package com.example.cinema.vo;

import com.example.cinema.po.Refund;

public class RefundVO {

    int hours;

    double get;

    public RefundVO(){

    }

    public RefundVO(Refund refund){
        hours = refund.getHours();
        get = refund.getGet();
    }

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
