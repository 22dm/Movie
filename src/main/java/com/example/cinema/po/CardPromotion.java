package com.example.cinema.po;

import com.example.cinema.vo.CardPromotionForm;

public class CardPromotion {

    //目标金额
    private int target;

    //赠送金额
    private int gift;

    public CardPromotion(){

    }

    public CardPromotion(CardPromotionForm cardPromotionForm){
        target = cardPromotionForm.getTarget();
        gift =  cardPromotionForm.getGift();
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

    public void setGift(int gift) {
        this.gift = gift;
    }
}
