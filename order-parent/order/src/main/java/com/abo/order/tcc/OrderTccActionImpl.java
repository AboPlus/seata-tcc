package com.abo.order.tcc;

import com.abo.order.entity.Order;
import com.abo.order.mapper.OrderMapper;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author Abo
 */
@Component
public class OrderTccActionImpl implements OrderTccAction {
    @Autowired
    private OrderMapper orderMapper;

    @Transactional
    @Override
    public boolean prepare(BusinessActionContext businessActionContext, Long id, Long userId, Long productId, Integer count, BigDecimal money) {
        orderMapper.create(new Order(id,userId,productId,count,money,0));
        // 添加一阶段成功标记
        ResultHolder.setResult(OrderTccAction.class, businessActionContext.getXid(), "p");
        return true;
    }

    @Transactional
    @Override
    public boolean commit(BusinessActionContext businessActionContext) {
        // 标记不存在，二阶段不再重复执行
        if (ResultHolder.getResult(OrderTccAction.class, businessActionContext.getXid()) == null ) {
            return true;
        }

        Long id = Long.valueOf(businessActionContext.getActionContext("id").toString());
        orderMapper.updateStatus(id, 1);

        //二阶段成功，删除标记
        ResultHolder.removeResult(OrderTccAction.class, businessActionContext.getXid());
        return true;
    }

    @Transactional
    @Override
    public boolean rollback(BusinessActionContext businessActionContext) {
        // 标记不存在，二阶段不再重复执行
        if (ResultHolder.getResult(OrderTccAction.class, businessActionContext.getXid()) == null ) {
            return true;
        }
        Long id = Long.valueOf(businessActionContext.getActionContext("id").toString());
        orderMapper.deleteById(id);
        //二阶段成功，删除标记
        ResultHolder.removeResult(OrderTccAction.class, businessActionContext.getXid());
        return true;
    }
}
