package com.abo.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Abo
 */
@FeignClient(name = "storage")
public interface StorageClient {

    @GetMapping("/decrease")
    String decrease(@RequestParam("productId") Long productId,
                    @RequestParam("count") Integer count);
}
