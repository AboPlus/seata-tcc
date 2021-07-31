package com.abo.storage.controller;

import com.abo.storage.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Abo
 */
@Slf4j
@RestController
public class StorageController {

    @Autowired
    private StorageService storageService;

    @GetMapping("/decrease")
    public String decrease(Long productId, Integer count){
        storageService.decrease(productId, count);
        return "减少库存成功";
    }


}
