package com.example.cinema.po;

import java.sql.Timestamp;

public class MovieLike {

    //电影 ID
    private int movieId;

    //用户 ID
    private int userId;

    //时间
    private Timestamp time;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
