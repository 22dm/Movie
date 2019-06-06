package com.example.cinema.bl.sales;

import com.example.cinema.vo.OrderPayForm;
import com.example.cinema.vo.RefundForm;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.OrderForm;

import java.util.List;

public interface OrderService {

    //创建订单
    ResponseVO add(OrderForm orderForm);

    //支付订单
    ResponseVO pay(OrderPayForm orderPayForm);

    //获得该场次的被锁座位和场次信息
    ResponseVO getBySchedule(int scheduleId);

    //获得用户的全部订单
    ResponseVO getByUserId(int userId);

     //获得某订单的信息
    ResponseVO get(int id);

    //退票
    ResponseVO refund(int orderId);

    //获得退票信息
    ResponseVO refundInfo(int orderId);

    //添加退款策略
    ResponseVO addRefund(RefundForm refundForm);

    //获得全部退款策略
    ResponseVO getRefund();

    //删除退款策略
    ResponseVO deleteRefund(int orderId);
}
