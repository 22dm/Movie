package com.example.cinema.po;

import com.example.cinema.vo.RefundForm;

public class Refund {

    int hours;

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
