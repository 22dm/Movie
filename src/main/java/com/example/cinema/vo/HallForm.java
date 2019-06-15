package com.example.cinema.vo;

import java.util.List;

public class HallForm {

    //影厅 ID
    private int id;

    //影厅名称
    private String name;

    //影厅行数
    private int row;

    //影厅列数
    private int column;

    //空缺座位表
    private SeatsStatusVO seats;

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

    public SeatsStatusVO getSeats() {
        return seats;
    }

    public void setSeats(SeatsStatusVO seats) {
        this.seats = seats;
    }
}
