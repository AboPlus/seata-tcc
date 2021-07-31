package com.abo.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @author Abo
 */
@FeignClient(name = "account")
public interface AccountClient {
    @GetMapping("/decrease")
    String decrease(@RequestParam("userId") Long userId,
                    @RequestParam("money") BigDecimal money);
}
