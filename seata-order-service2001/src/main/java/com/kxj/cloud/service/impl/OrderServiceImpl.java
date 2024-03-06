package com.kxj.cloud.service.impl;

import com.kxj.cloud.apis.AccountFeignApi;
import com.kxj.cloud.apis.StorageFeignApi;
import com.kxj.cloud.entities.Order;
import com.kxj.cloud.mapper.OrderMapper;
import com.kxj.cloud.service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;


@Service
@Slf4j
public class OrderServiceImpl implements OrderService
{
    @Resource
    private OrderMapper orderMapper;

    @Resource
    private StorageFeignApi storageFeignApi;

    @Resource
    private AccountFeignApi accountFeignApi;

    @Override
    @GlobalTransactional(name = "kxj",rollbackFor = Exception.class)
    public void create(Order order)
    {
        String xid = RootContext.getXID();
        log.info("[create] 开始创建订单，xid = {}", xid);
        order.setStatus(0);
        int result = orderMapper.insertSelective(order);
        Order orderFromDB=null;
        if (result>0){
            orderFromDB=orderMapper.selectOne(order);
            log.info("[create] 创建订单成功，orderId = {}", orderFromDB.getId());
            System.out.println();
            log.info("订单微服务开始调用库存");
            storageFeignApi.decrease(orderFromDB.getProductId(), orderFromDB.getCount());
            log.info("订单微服务结束调用库存");
            System.out.println();
            log.info("订单微服务开始调用账户");
            accountFeignApi.decrease(orderFromDB.getUserId(), orderFromDB.getMoney());
            log.info("订单微服务结束调用账户");
            orderFromDB.setStatus(1);

            Example example=new Example(Order.class);
            Example.Criteria criteria=example.createCriteria();
            criteria.andEqualTo("userId",orderFromDB.getUserId());
            criteria.andEqualTo("status",0);
            int update = orderMapper.updateByExampleSelective(orderFromDB, example);
            log.info("[create] 更新订单状态成功，update = {}", update);
            log.info("orderFromDB info: {}", orderFromDB);

        }
        System.out.println();
        log.info("[create] 创建订单完成，xid = {}", xid);
    }
}
