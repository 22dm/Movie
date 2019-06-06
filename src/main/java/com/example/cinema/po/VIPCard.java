package com.example.cinema.po;


import com.example.cinema.vo.CardBuyForm;

import java.sql.Timestamp;

public class VIPCard {

    //会员卡ID
    private int id;

    //用户ID
    private int userId;

    //余额
    private int balance;

    //加入时间
    private Timestamp joinDate;

    public VIPCard() {

    }

    public VIPCard(CardBuyForm cardBuyForm) {
        userId = cardBuyForm.getUserId();
        balance = 0;
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
}
