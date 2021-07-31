package com.abo.order.service;

import com.abo.order.entity.Order;
import com.abo.order.feign.AccountClient;
import com.abo.order.feign.EasyIdClient;
import com.abo.order.feign.StorageClient;
import com.abo.order.mapper.OrderMapper;
import com.abo.order.tcc.OrderTccAction;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author Abo
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderTccAction orderTccAction;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private EasyIdClient easyIdClient;
    @Autowired
    private AccountClient accountClient;
    @Autowired
    private StorageClient storageClient;

    @GlobalTransactional
    @Override
    public void create(Order order) {
        // 调用发号器获取订单id
        String order_business = easyIdClient.nextId("order_business");
        Long orderId = Long.valueOf(order_business);
        order.setId(orderId);

        /*// 临时产生订单id，调用发号器后这里的代码删除
        long orderId = Math.abs(new Random().nextLong());
        order.setId(orderId);*/

        // 不再直接执行业务数据操作，而是调用TccAction的第一阶段方法，冻结数据
        // orderMapper.create(order);
        /**
         * orderTccAction是一个动态代理对象，使用AOP切入了代码
         * 在切面代码中会新建上下文对象，传入到原始方法，传什么其实都是代理对象自己创建businessActionContext
         */
        orderTccAction.prepare(
                null,
                order.getId(),
                order.getUserId(),
                order.getProductId(),
                order.getCount(),
                order.getMoney());

        // 减少库存
        storageClient.decrease(order.getProductId(), order.getCount());

        // 扣减账户
        accountClient.decrease(order.getUserId(), order.getMoney());

    }
}
