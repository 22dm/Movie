package com.example.cinema.vo;

import com.example.cinema.po.CardCharge;

import java.sql.Timestamp;

public class CardChargeVO {

    //会员卡Id
    private int cardId;

    //充值金额
    private int charge;

    //赠送金额
    private int gift;

    //支付方式
    private int mention;

    //支付时间
    private Timestamp time;

    public CardChargeVO(){

    }

    public CardChargeVO(CardCharge cardCharge){
        cardId = cardCharge.getCardId();
        charge = cardCharge.getCharge();
        gift = cardCharge.getGift();
        mention = cardCharge.getMention();
        time = cardCharge.getTime();
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    public int getGift() {
        return gift;
    }

    public void setGift(int gift) {
        this.gift = gift;
    }

    public int getMention() {
        return mention;
    }

    public void setMention(int mention) {
        this.mention = mention;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
