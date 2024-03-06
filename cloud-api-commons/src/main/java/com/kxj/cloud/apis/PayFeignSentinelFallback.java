package com.kxj.cloud.apis;

import com.kxj.cloud.resp.ResultData;
import com.kxj.cloud.resp.ReturnCodeEnum;
import org.springframework.stereotype.Component;

@Component
public class PayFeignSentinelFallback implements PayFeignSentinelApi
{

    @Override
    public ResultData getPayByOrderNo(String orderNo) {
        return ResultData.fail(ReturnCodeEnum.RC500.getCode(), "对方服务宕机");
    }
}
