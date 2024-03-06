package com.kxj.cloud.controller;

import com.kxj.cloud.resp.ResultData;
import com.kxj.cloud.service.StorageService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageController {
    @Resource
    private StorageService storageService;

    @RequestMapping("/storage/decrease")
    public ResultData decrease(Long productId, Integer count) {

        storageService.decrease(productId, count);
        return ResultData.success("扣减库存成功!");
    }

}
