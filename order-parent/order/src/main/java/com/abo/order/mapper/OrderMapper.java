package com.abo.order.mapper;

import com.abo.order.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author Abo
 */
public interface OrderMapper extends BaseMapper<Order> {
    void create(Order order);

    /**
     * 修改订单状态
     */
    void updateStatus(Long id, Integer status);

    /**
     * 删除订单，使用继承的deleteById()
     */

}
