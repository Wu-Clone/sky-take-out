package com.sky.service;

import com.sky.vo.OrderReportVO;
import com.sky.vo.SalesTop10ReportVO;
import com.sky.vo.TurnoverReportVO;
import com.sky.vo.UserReportVO;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

public interface ReportService {
    // 营业额数据统计
    TurnoverReportVO getTurnover(LocalDate startDate, LocalDate endDate);

    // 用户数据统计
    UserReportVO getUserStatistics(LocalDate startDate, LocalDate endDate);

    // 根据时间区间 统计订单数量
    OrderReportVO getOrderStatistics(LocalDate begin, LocalDate end);

    // 查询指定时间区间内的销量排名top10
    SalesTop10ReportVO getSalesTop10(LocalDate begin, LocalDate end);

    // 导出近30天的运营数据报表
    void exportBusinessData(HttpServletResponse response);
}
