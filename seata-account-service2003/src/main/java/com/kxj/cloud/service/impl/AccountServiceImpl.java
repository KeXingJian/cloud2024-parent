package com.kxj.cloud.service.impl;

import com.kxj.cloud.mapper.AccountMapper;
import com.kxj.cloud.service.AccountService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    @Override
    public void decrease(Long userId, Long money) {
        log.info("------->account-service中扣减账户余额开始");

        accountMapper.decrease(userId,money);

        //myTimeOut();
        //int age = 10/0;
        log.info("------->account-service中扣减账户余额结束");

    }

    private static void myTimeOut()
    {
        try { TimeUnit.SECONDS.sleep(65); } catch (InterruptedException e) { e.printStackTrace(); }
    }
}
