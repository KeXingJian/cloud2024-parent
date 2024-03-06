package com.kxj.cloud.controller;

import com.kxj.cloud.entities.Pay;
import com.kxj.cloud.entities.PayDTO;
import com.kxj.cloud.resp.ResultData;
import com.kxj.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Tag(name = "支付微服务", description = "支付CRUD")
@RestController
@Slf4j
public class PayController {
    @Resource
    private PayService payService;

    @PostMapping("/pay/add")
    @Operation(summary = "添加支付信息",description = "新增支付流水方法，json串参数")
    public ResultData<String> addPay(@RequestBody Pay pay){
        System.out.println(pay.toString());
        int add = payService.add(pay);
        return ResultData.success("成功插入记录，返回值：" + add);
    }

    @DeleteMapping("/pay/delete/{id}")
    @Operation(summary = "删除支付信息",description = "根据id删除支付流水方法")
    public ResultData<Integer> deletePay(@PathVariable("id") Integer id){
        return ResultData.success(payService.delete(id));
    }

    @PutMapping("/pay/update")
    public ResultData<String> updatePay(@RequestBody PayDTO payDTO){
        Pay pay=new Pay();
        BeanUtils.copyProperties(payDTO,pay);
        return ResultData.success("成功修改记录，返回值：" + payService.update(pay));

    }

    @GetMapping("/pay/get/{id}")
    @Operation(summary = "查询支付信息",description = "根据id查询支付流水方法")
    public ResultData<Pay> getById(@PathVariable("id") Integer id){
        return ResultData.success(payService.getById(id));
    }

    @GetMapping("/pay/all")
    @Operation(summary = "查询所有支付信息",description = "查询所有支付流水方法")
    public ResultData<List<Pay>> getAll(){
        return ResultData.success(payService.getAll());
    }

    @Value("${server.port}")
    private String port;

    @GetMapping("/pay/get/info")
    public String getInfo(@Value("${kxj.info}")String kxjinfo){
        try {
            TimeUnit.SECONDS.sleep(62);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "当前端口："+port+"，获取到的配置文件信息："+kxjinfo;
    }
}
