package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    void insert(Orders order);

    // 分页条件查询，并按照下单时间排序
    Page<Orders> pageQuery(OrdersPageQueryDTO ordersPageQueryDTO);
}
