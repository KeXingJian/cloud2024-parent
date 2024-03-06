package com.kxj.cloud.apis;

import com.kxj.cloud.entities.PayDTO;
import com.kxj.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("cloud-gateway")
public interface PayFeignApi {

    @PostMapping("/pay/add")
    public ResultData addPay(@RequestBody PayDTO payDTO);

    @DeleteMapping("/pay/delete/{id}")
    public ResultData deletePay(@PathVariable("id") Integer id);

    @PutMapping("/pay/update")
    public ResultData updatePay(@RequestBody PayDTO payDTO);

    @GetMapping("/pay/get/{id}")
    public ResultData getPay(@PathVariable("id") Integer id);

    @GetMapping("/pay/all")
    public ResultData getAll();

    @GetMapping("/pay/get/info")
    public String getInfo();

    @GetMapping(value = "/pay/circuit/{id}")
    public String myCircuit(@PathVariable("id") Integer id);

    @GetMapping(value = "/pay/bulkhead/{id}")
    public String myBulkhead(@PathVariable("id") Integer id);

    @GetMapping(value = "/pay/ratelimit/{id}")
    public String myRatelimit(@PathVariable("id") Integer id);

    @GetMapping(value = "/pay/micrometer/{id}")
    public String myMicrometer(@PathVariable("id") Integer id);

    /**
     * GateWay进行网关测试案例01
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/gateway/get/{id}")
    public ResultData getById(@PathVariable("id") Integer id);

    /**
     * GateWay进行网关测试案例02
     * @return
     */
    @GetMapping(value = "/pay/gateway/info")
    public ResultData<String> getGatewayInfo();

}
