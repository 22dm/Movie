package com.example.cinema.vo;

import com.example.cinema.po.VIPCard;

import java.sql.Timestamp;
import java.util.List;

public class VIPCardVO {

    private int id;

    private int userId;

    private int balance;

    private Timestamp joinDate;

    List<CardChargeVO> charges;

    List<CardPromotionVO> promotions;

    public VIPCardVO(){

    }

    public VIPCardVO(VIPCard vipCard){
        id = vipCard.getId();
        userId = vipCard.getUserId();
        balance = vipCard.getBalance();
        joinDate = vipCard.getJoinDate();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Timestamp getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Timestamp joinDate) {
        this.joinDate = joinDate;
    }

    public List<CardChargeVO> getCharges() {
        return charges;
    }

    public void setCharges(List<CardChargeVO> charges) {
        this.charges = charges;
    }

    public List<CardPromotionVO> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<CardPromotionVO> promotions) {
        this.promotions = promotions;
    }
}
