package com.kxj.cloud.controller;


import cn.hutool.core.date.DateUtil;
import com.kxj.cloud.apis.PayFeignApi;
import com.kxj.cloud.entities.PayDTO;
import com.kxj.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping("/consumer/pay/add")
    public ResultData addPay(@RequestBody PayDTO payDTO){
        return payFeignApi.addPay(payDTO);
    }

    @GetMapping("/consumer/pay/delete/{id}")
    public ResultData deletePay(@PathVariable("id") Integer id){
        return payFeignApi.deletePay(id);
    }

    @GetMapping("/consumer/pay/update")
    public ResultData updatePay(@RequestBody PayDTO payDTO){
       return payFeignApi.updatePay(payDTO);
    }

    @GetMapping("/consumer/pay/get/{id}")
    public ResultData getPay(@PathVariable("id") Integer id){
        return payFeignApi.getPay(id);
    }

    @GetMapping("/consumer/pay/all")
    public ResultData getAll(){
        return payFeignApi.getAll();
    }

    @GetMapping("/consumer/pay/get/info")
    public String getInfo(){
        String info="";
        try {
            System.out.println(DateUtil.now());
            info= payFeignApi.getInfo();
        }catch (Exception e){
            System.out.println(DateUtil.now());
            e.printStackTrace();
        }
        return info;
    }
    @Resource
    private DiscoveryClient discoveryClient;
    @GetMapping("/consumer/discovery")
    public String discovery()
    {
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            System.out.println(element);
        }

        System.out.println("===================================");

        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance element : instances) {
            System.out.println(element.getServiceId()+"\t"+element.getHost()+"\t"+element.getPort()+"\t"+element.getUri());
        }

        return instances.get(0).getServiceId()+":"+instances.get(0).getPort();
    }

}
