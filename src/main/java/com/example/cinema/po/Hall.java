package com.example.cinema.po;

import com.example.cinema.vo.HallForm;

public class Hall {

    //影厅 ID
    private int id;

    //影厅名称
    private String name;

    //影厅行数
    private int row;

    //影厅列数
    private int column;

    public Hall(){

    }

    public Hall(HallForm hallForm){
        id = hallForm.getId();
        name = hallForm.getName();
        row = hallForm.getRow();
        column = hallForm.getColumn();
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
