package com.example.cinema.vo;

import com.example.cinema.po.Seat;

/*
    座位表单，用于：
    传递座位信息
 */
public class SeatVO {

    //列号
    private int columnIndex;

    //排号
    private int rowIndex;

    public SeatVO(){

    }

    public SeatVO(Seat seat){
        columnIndex = seat.getColumnIndex();
        rowIndex = seat.getRowIndex();
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }
}
