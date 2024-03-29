package com.example.cinema.vo;

import com.example.cinema.po.Hall;

public class HallVO {

    //影厅 ID
    private int id;

    //影厅名称
    private String name;

    //影厅行数
    private int row;

    //影厅列数
    private int column;

    public HallVO(){

    }

    public HallVO(Hall hall){
        id = hall.getId();
        name = hall.getName();
        row = hall.getRow();
        column = hall.getColumn();
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
}
