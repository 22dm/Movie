package com.example.cinema.vo;

import com.example.cinema.po.UserCost;

public class UserCostVO {

    //用户id
    private int id;

    //用户名
    private String username;

    //消费总额
    private int cost;

    public UserCostVO(){

    }

    public UserCostVO(UserCost userCost){
        id = userCost.getId();
        username = userCost.getUsername();
        cost = userCost.getCost();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
