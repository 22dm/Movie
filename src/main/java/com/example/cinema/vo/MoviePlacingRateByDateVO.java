package com.example.cinema.vo;
import com.example.cinema.po.MoviePlacingRateByDate;

import java.util.*;
public class MoviePlacingRateByDateVO {
    private Date date;
    private double placing;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPlacing() {
        return placing;
    }

    public void setPlacing(double placing) {
        this.placing = placing;
    }



    public MoviePlacingRateByDateVO(){

    }
    public MoviePlacingRateByDateVO(MoviePlacingRateByDate moviePlacingRateByDate){

        this.date = moviePlacingRateByDate.getDate();
        this.placing = moviePlacingRateByDate.getPlacing();
    }




}
