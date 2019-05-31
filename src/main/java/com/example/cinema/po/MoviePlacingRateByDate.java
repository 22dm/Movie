package com.example.cinema.po;

import java.util.Date;

public class MoviePlacingRateByDate {
    private Date date;

    public double getPlacing() {
        return placing;
    }

    public void setPlacing(double placing) {
        this.placing = placing;
    }

    private double placing;

    public Date getDate(){
        return date;
    }

    public void setDate(Date date){
        this.date = date;
    }



}
