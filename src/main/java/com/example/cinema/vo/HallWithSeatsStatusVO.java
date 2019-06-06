package com.example.cinema.vo;

import com.example.cinema.po.Hall;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class HallWithSeatsStatusVO {

    //影厅 ID
    private int id;

    //影厅名称
    private String name;

    //影厅行数
    private int row;

    //影厅列数
    private int column;

    //座位状态表
    private int[][] seats;

    public HallWithSeatsStatusVO(){

    }

    public HallWithSeatsStatusVO(Hall hall){
        id = hall.getId();
        name = hall.getName();
        row = hall.getRow();
        column = hall.getColumn();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HallWithSeatsStatusVO)) return false;
        HallWithSeatsStatusVO that = (HallWithSeatsStatusVO) o;
        return id == that.id &&
                row == that.row &&
                column == that.column &&
                Objects.equals(name, that.name) &&
                Arrays.deepEquals(seats, that.seats);
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

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int[][] getSeats() {
        return seats;
    }

    public void setSeats(int[][] seats) {
        this.seats = seats;
    }
}
