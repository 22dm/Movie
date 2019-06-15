package com.example.cinema.data.sales;

import com.example.cinema.po.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Mapper
public interface TicketMapper {

    void addOrder(Order order);

    void addSeat(Seat seat);

    void payOrder(OrderPay orderPay);

    List<Seat> selectSeatsBySchedule(int scheduleId);

    List<Seat> selectSeatsByOrderId(int orderId);

    List<Order> selectOrdersByUserId(int userId);

    Order selectOrdersById(int id);

    List<Refund> selectRefund();

    double selectGet(int hours);

    void insertRefund(Refund refund);

    void deleteRefund(int hours);

    void refund(int orderId);

    @Scheduled(cron = "0/1 * * * * ?")
    void cleanExpiredTicket();
}

