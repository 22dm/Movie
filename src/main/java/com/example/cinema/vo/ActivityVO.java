package com.example.cinema.vo;

import com.example.cinema.po.Activity;
import com.example.cinema.po.Coupon;

import java.sql.Timestamp;
import java.util.List;

public class ActivityVO {

    //活动 ID
    private int id;

    //活动名称
    private String name;

    //活动描述
    private String description;

    //开始时间
    private Timestamp startTime;

    //结束时间
    private Timestamp endTime;

    //活动电影列表
    private List<String> movies;

    //包含的优惠券
    private CouponVO coupon;


    public ActivityVO() {

    }

    public ActivityVO(Activity activity) {
        id = activity.getId();
        name = activity.getName();
        description = activity.getDescription();
        startTime = activity.getStartTime();
        endTime = activity.getEndTime();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<String> getMovies() {
        return movies;
    }

    public void setMovies(List<String> movies) {
        this.movies = movies;
    }

    public CouponVO getCoupon() {
        return coupon;
    }

    public void setCoupon(CouponVO coupon) {
        this.coupon = coupon;
    }
}
