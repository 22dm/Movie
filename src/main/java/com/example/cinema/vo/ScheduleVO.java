package com.example.cinema.vo;

import com.example.cinema.po.Schedule;

import java.util.Date;

public class ScheduleVO {

    //排片 Id
    private Integer id;

    //影厅
    private HallVO hall;

    //电影
    private MovieVO movie;

    //票价
    private int fare;

    //开始时间
    private Date startTime;

    //结束时间
    private Date endTime;

    //类型
    private String type;

    public ScheduleVO(){

    }

    public ScheduleVO(Schedule schedule){
        id = schedule.getId();
        fare = schedule.getFare();
        startTime = schedule.getStartTime();
        type = schedule.getType();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public HallVO getHall() {
        return hall;
    }

    public void setHall(HallVO hall) {
        this.hall = hall;
    }

    public MovieVO getMovie() {
        return movie;
    }

    public void setMovie(MovieVO movie) {
        this.movie = movie;
    }

    public int getFare() {
        return fare;
    }

    public void setFare(int fare) {
        this.fare = fare;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
