package com.sky.service;

import com.sky.dto.*;
import com.sky.result.PageResult;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;

public interface OrderService {
    OrderSubmitVO submitOrder(OrdersSubmitDTO ordersSubmitDTO);

    // 客户订单分页查询
    PageResult pageQuery4User(int page, int pageSize, Integer status);

    // 条件搜索订单
    PageResult conditionSearch(OrdersPageQueryDTO ordersPageQueryDTO);

    // 各个状态的订单数量
    OrderStatisticsVO statistics();

    // 查询订单详情
    OrderVO details(Long id);

    // 用户取消订单
    void userCancelById(Long id) throws Exception;

    // 再来一单
    void repetition(Long id);

    // 接单
    void confirm(OrdersConfirmDTO ordersConfirmDTO
    );

    // 拒单
    void rejection(OrdersRejectionDTO ordersRejectionDTO) throws Exception;

    // 取消订单
    void cancel(OrdersCancelDTO ordersCancelDTO) throws Exception;

    // 派送订单
    void delivery(Long id);

    // 完成订单
    void complete(Long id);

    // 用户催单
    void reminder(Long id);
}
