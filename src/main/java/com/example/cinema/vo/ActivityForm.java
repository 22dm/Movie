package com.example.cinema.vo;

import java.sql.Timestamp;
import java.util.List;

public class ActivityForm {

    //活动名称
    private String name;

    //活动描述
    private String description;

    //开始时间
    private Timestamp startTime;

    //结束时间
    private Timestamp endTime;

    //优惠电影 ID 列表
    private List<Integer> movies;

    //优惠券 ID
    private int couponId;


    public ActivityForm() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public List<Integer> getMovies() {
        return movies;
    }

    public void setMovies(List<Integer> movies) {
        this.movies = movies;
    }

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }
}
