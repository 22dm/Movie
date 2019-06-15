package com.example.cinema.po;

import com.example.cinema.vo.ActivityForm;

import java.sql.Timestamp;

public class Activity {

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

    //包含的优惠券
    private int couponId;

    public Activity(){

    }

    public Activity(ActivityForm activityForm){
        name = activityForm.getName();
        description = activityForm.getDescription();
        startTime = activityForm.getStartTime();
        endTime = activityForm.getEndTime();
        couponId = activityForm.getCouponId();
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

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }
}
