package com.sky.task;

import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import com.sky.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class OrderTask {
    //    @Scheduled(cron = "0/5 * * * * ?")
    public void test() {
        log.info("定时任务执行 test {}", new Date());
    }

    @Autowired
    private OrderMapper orderMapper;

    // 处理支付超时的订单
    @Scheduled(cron = "0 * * * * *")
    public void processTimeoutOrder() {
        log.info("处理支付超时的订单：{}", new Date());
        LocalDateTime time = LocalDateTime.now().plusMinutes(-15);

        List<Orders> ordersList = orderMapper.getByStatusAndOrdertimeLT(Orders.PENDING_PAYMENT, time);
        if (ordersList!=null && !ordersList.isEmpty()) {
            ordersList.forEach(order -> {
                order.setStatus(Orders.CANCELLED);
                order.setCancelReason("支付超时，自动取消");
                order.setCancelTime(LocalDateTime.now());
                orderMapper.update(order);
            });
        }
    }

    // 处理派送中的订单
    @Scheduled(cron = "0 0 1 * * *")
    public void processDeliveryOrder() {
        log.info("处理派送中的订单：{}", new Date());
        LocalDateTime time = LocalDateTime.now().plusMinutes(-60);
        List<Orders> ordersList = orderMapper.getByStatusAndOrdertimeLT(Orders.DELIVERY_IN_PROGRESS, time);
        if (ordersList!=null && !ordersList.isEmpty()) {
            ordersList.forEach(order -> {
                order.setStatus(Orders.COMPLETED);
                orderMapper.update(order);
            });
        }
    }
}
