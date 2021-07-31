package com.abo.order.controller;

import com.abo.order.entity.Order;
import com.abo.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Abo
 */
@Slf4j
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/create")
    public String create(Order order){
        orderService.create(order);
        return "创建订单成功";
    }
}
