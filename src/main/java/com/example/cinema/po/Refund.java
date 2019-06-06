package com.example.cinema.po;

import com.example.cinema.vo.RefundForm;

public class Refund {

    //剩余小时数
    int hours;

    //退款比例
    double get;

    public Refund(){

    }

    public Refund(RefundForm refundForm){
        hours = refundForm.getHours();
        get = refundForm.getGet();
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
