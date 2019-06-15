package com.example.cinema.po;

import com.example.cinema.vo.MovieForm;

import java.util.Date;

public class Movie {

    //电影 ID
    private int id;

    //电影名称
    private String name;

    //电影海报 URL
    private String posterUrl;

    //电影类型
    private String type;

    //制片国家 / 地区
    private String country;

    //语言
    private String language;

    //上映时间
    private Date startDate;

    //片长
    private int length;

    //简介
    private String description;

    //主演
    private String actor;

    //状态
    private Integer status;

    public Movie(){

    }

    public Movie(MovieForm movieForm){
        id = movieForm.getId();
        name = movieForm.getName();
        posterUrl = movieForm.getPosterUrl();
        type = movieForm.getType();
        country = movieForm.getCountry();
        language = movieForm.getLanguage();
        startDate = movieForm.getStartDate();
        length = movieForm.getLength();
        description = movieForm.getDescription();
        actor = movieForm.getActor();
        status = 0;
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

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
