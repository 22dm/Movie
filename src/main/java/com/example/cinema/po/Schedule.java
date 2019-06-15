package com.example.cinema.po;

import com.example.cinema.vo.ScheduleForm;

import java.util.Date;

public class Schedule {

    //排片 ID
    private Integer id;

    //影厅 ID
    private Integer hallId;

    //电影 ID
    private Integer movieId;

    //开始时间
    private Date startTime;

    //票价
    private int fare;

    //类型
    private String type;

    public Schedule(){

    }

    public Schedule(ScheduleForm scheduleForm){
        id = scheduleForm.getId();
        hallId = scheduleForm.getHallId();
        movieId = scheduleForm.getMovieId();
        startTime = scheduleForm.getStartTime();
        fare = scheduleForm.getFare();
        type = scheduleForm.getType();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHallId() {
        return hallId;
    }

    public void setHallId(Integer hallId) {
        this.hallId = hallId;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public int getFare() {
        return fare;
    }

    public void setFare(int fare) {
        this.fare = fare;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
