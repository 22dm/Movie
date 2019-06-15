package com.example.cinema.vo;

import com.example.cinema.po.UserCost;

public class UserCostVO {

    private int id;

    private String username;

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
