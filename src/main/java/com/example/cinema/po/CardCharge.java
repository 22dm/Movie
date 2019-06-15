package com.example.cinema.po;

import com.example.cinema.vo.CardChargeForm;

import java.sql.Timestamp;

public class CardCharge {

    private int cardId;

    private int charge;

    private int gift;

    private int mention;

    private Timestamp time;

    public CardCharge(){

    }

    public CardCharge(CardChargeForm cardChargeForm){
        cardId = cardChargeForm.getCardId();
        charge = cardChargeForm.getPayForm().getAmount();
        mention = cardChargeForm.getPayForm().getMention();
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
