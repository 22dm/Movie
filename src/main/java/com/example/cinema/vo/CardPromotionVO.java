package com.example.cinema.vo;

import com.example.cinema.po.CardPromotion;

public class CardPromotionVO {

    private int target;

    private int gift;

    public CardPromotionVO(){

    }

    public CardPromotionVO(CardPromotion cardPromotion){
        target = cardPromotion.getTarget();
        gift =  cardPromotion.getGift();
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getGift() {
        return gift;
    }

    public void getGift(int gift) {
        this.gift = gift;
    }
}
