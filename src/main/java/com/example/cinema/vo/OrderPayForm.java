package com.example.cinema.vo;

public class OrderPayForm {

    //订单 ID
    private int orderId;

    //优惠券 ID
    private int couponId;

    //支付表单
    private PayForm payForm;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    public PayForm getPayForm() {
        return payForm;
    }

    public void setPayForm(PayForm payForm) {
        this.payForm = payForm;
    }
}
