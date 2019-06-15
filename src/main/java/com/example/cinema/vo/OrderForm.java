package com.example.cinema.vo;

import java.util.List;

/*
    订单表单，用于：
    前端传输此格式的数据来创建订单
 */
public class OrderForm {

    //用户 ID
    private int userId;

    //排片 ID
    private int scheduleId;

    //座位列表
    private List<SeatVO> seats;

    //总价
    private int price;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public List<SeatVO> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatVO> seats) {
        this.seats = seats;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
