package com.example.cinema.vo;

import com.example.cinema.po.Refund;

public class RefundForm {

    //剩余小时数
    int hours;

    //退款比例
    double get;

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public double getGet() {
        return get;
    }

    public void setGet(double get) {
        this.get = get;
    }
}
