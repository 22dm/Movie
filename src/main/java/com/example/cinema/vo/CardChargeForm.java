package com.example.cinema.vo;

public class CardChargeForm {

    //会员卡ID
    private int cardId;

    //支付表单
    private PayForm payForm;

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public PayForm getPayForm() {
        return payForm;
    }

    public void setPayForm(PayForm payForm) {
        this.payForm = payForm;
    }
}
