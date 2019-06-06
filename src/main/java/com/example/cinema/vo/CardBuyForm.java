package com.example.cinema.vo;

public class CardBuyForm {

    //用户Id
    private int userId;

    //支付表单
    private PayForm payForm;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public PayForm getPayForm() {
        return payForm;
    }

    public void setPayForm(PayForm payForm) {
        this.payForm = payForm;
    }
}
