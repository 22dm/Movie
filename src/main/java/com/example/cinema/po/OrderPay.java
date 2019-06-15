package com.example.cinema.po;

import com.example.cinema.vo.OrderPayForm;

public class OrderPay {

    //订单 Id
    private int orderId;

    //支付方式
    private int method;

    //实际支付金额
    private int price;

    public OrderPay() {

    }

    public OrderPay(OrderPayForm orderPayForm){
        orderId = orderPayForm.getOrderId();
        method = orderPayForm.getPayForm().getMention();
        price = orderPayForm.getPayForm().getAmount();
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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
