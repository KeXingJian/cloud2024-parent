package com.kxj.cloud.controller;

import com.kxj.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.kxj.cloud.entities.PayDTO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class OrderController {
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";
    @Resource
    private RestTemplate restTemplate;
    @GetMapping("/consumer/pay/add")
    public ResultData addPay(@RequestBody PayDTO payDTO){
        return restTemplate.postForObject(PAYMENT_URL+"/pay/add",payDTO,ResultData.class);
    }

    @GetMapping("/consumer/pay/delete/{id}")
    public ResultData deletePay(@PathVariable("id") Integer id){
        Map<String, Object> map=new HashMap<>();
        map.put("id",id);
        ResponseEntity<ResultData> exchange = restTemplate.exchange(PAYMENT_URL + "/pay/delete/"+id, HttpMethod.DELETE, null, ResultData.class, map);
        return exchange.getBody();
    }

    @GetMapping("/consumer/pay/update")
    public ResultData updatePay(@RequestBody PayDTO payDTO){
        Map<String, Object> map=new HashMap<>();
        map.put("id",payDTO.getId());
        map.put("payNo",payDTO.getPayNo());
        map.put("orderNo",payDTO.getOrderNo());
        map.put("userId",payDTO.getUserId());
        map.put("amount",payDTO.getAmount());
        ResponseEntity<ResultData> response = restTemplate.exchange(PAYMENT_URL + "/pay/update", HttpMethod.PUT, null, ResultData.class, map);
        return response.getBody();
    }

    @GetMapping("/consumer/pay/get/{id}")
    public ResultData getPay(@PathVariable("id") Integer id){
        return restTemplate.getForObject(PAYMENT_URL+"/pay/get/"+id,ResultData.class,id);
    }

    @GetMapping("/consumer/pay/all")
    public ResultData getAll(){
        return restTemplate.getForObject(PAYMENT_URL+"/pay/all",ResultData.class);
    }

    @GetMapping("/consumer/pay/get/info")
    public String getInfo(){
        return restTemplate.getForObject(PAYMENT_URL+"/pay/get/info",String.class);
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
