package com.abo.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 调用哪个服务 --> @FeignClient
 * 什么路径 --> @GetMapping
 * 什么参数 -- > @RequestParam
 * @author Abo
 */
@FeignClient(name = "easy-id-generator")
public interface EasyIdClient {
    @GetMapping("/segment/ids/next_id")
    String nextId(@RequestParam("businessType") String businessType);
}
