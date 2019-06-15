package com.example.cinema.po;

import com.example.cinema.vo.SeatsStatusVO;

public class InvalidSeat {

    //影厅 ID
    private int id;

    //影厅行数
    private int row;

    //影厅列数
    private int column;

    public InvalidSeat(){

    }

    public InvalidSeat(SeatsStatusVO seatsStatusVO) {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
