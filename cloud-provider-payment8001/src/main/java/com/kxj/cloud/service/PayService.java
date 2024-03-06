package com.kxj.cloud.service;

import com.kxj.cloud.entities.Pay;

import java.util.List;

public interface PayService {
    //根据支付信息添加支付
    int add(Pay pay);
    //根据id删除支付
    int delete(Integer id);
    //根据支付信息更新支付
    int update(Pay pay);
    //根据id获取支付信息
    Pay getById(Integer id);
    //获取所有支付信息
    List<Pay> getAll();

}
