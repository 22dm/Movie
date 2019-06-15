package com.example.cinema.vo;

import com.example.cinema.po.Order;

import java.sql.Timestamp;
import java.util.List;

public class OrderVO {

    //订单 ID
    private int id;

    //用户 ID
    private int userId;

    //排片
    private ScheduleVO schedule;

    //座位列表
    private List<SeatVO> seats;

    //订单状态
    private int status;

    //下单时间
    private Timestamp time;

    //支付方式
    private int method;

    //实际支付金额
    private int price;

    public OrderVO(){

    }

    public OrderVO(Order order) {
        id = order.getId();
        userId = order.getUserId();
        status = order.getStatus();
        time = order.getTime();
        method = order.getMethod();
        price = order.getPrice();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public ScheduleVO getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleVO schedule) {
        this.schedule = schedule;
    }

    public List<SeatVO> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatVO> seats) {
        this.seats = seats;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getMethod() {
        return method;
    }

    public void setMethod(int method) {
        this.method = method;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
