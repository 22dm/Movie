package com.example.cinema.po;


import com.example.cinema.vo.CardBuyForm;

import java.sql.Timestamp;

public class VIPCard {

    private int id;

    private int userId;

    private int balance;

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
