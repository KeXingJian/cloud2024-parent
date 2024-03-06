package com.kxj.cloud.mygateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
@Slf4j
@Component
public class MyGlobalFilter implements GlobalFilter, Ordered {

    public static final String BEGIN_VISIT_TIME = "begin_visit_time";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        exchange.getAttributes().put(BEGIN_VISIT_TIME, System.currentTimeMillis());

        return chain.filter(exchange).then(Mono.fromRunnable(() ->{
            Long attribute = exchange.getAttribute(BEGIN_VISIT_TIME);
            if (attribute != null){
                log.info("访问主机:"+exchange.getRequest().getURI().getHost());
                log.info("访问接口端口:"+exchange.getRequest().getURI().getPort());
                log.info("访问接口路径:"+exchange.getRequest().getURI().getPath());
                log.info("访问接口参数:"+exchange.getRequest().getQueryParams());
                log.info("请求耗时:"+(System.currentTimeMillis()-attribute)+"ms");
                log.info("------------------------------------------------");
                System.out.println();
            }
        }));
    }

    @Override
    public int getOrder() {
        return -100;
    }
}