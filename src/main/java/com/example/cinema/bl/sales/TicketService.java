package com.example.cinema.bl.sales;

import com.example.cinema.vo.OrderPayForm;
import com.example.cinema.vo.RefundForm;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.OrderForm;

import java.util.List;

public interface TicketService {

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

    ResponseVO refund(int orderId);

    ResponseVO addRefund(RefundForm refundForm);

    ResponseVO getRefund();

    ResponseVO deleteRefund(int orderId);
}
