package com.example.cinema.vo;

// 买票时获取已被占用的座位
public class ScheduleWithSeatVO {
    /**
     * 排片
     */
    private ScheduleVO schedule;
    /**
     * 座位
     */
    private int[][] seats;

    public ScheduleVO getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleVO schedule) {
        this.schedule = schedule;
    }

    public int[][] getSeats() {
        return seats;
    }

    public void setSeats(int[][] seats) {
        this.seats = seats;
    }
}
