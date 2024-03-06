package com.kxj.cloud.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    public Retryer feignRetryer() {
        //return new Retryer.Default(100, 1, 3); // 最大重试次数为3
        return Retryer.NEVER_RETRY; // 不重试
    }
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
