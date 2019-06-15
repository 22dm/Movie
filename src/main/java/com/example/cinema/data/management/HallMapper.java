package com.example.cinema.data.management;

import com.example.cinema.po.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HallMapper {

    //查询所有影厅
    List<Hall> selectAllHall();

    //查询指定影厅
    Hall selectHall(int id);

    //添加影厅
    int addHall(Hall hall);

    //更新影厅
    void updateHall(Hall hall);

    //删除影厅
    void deleteHall(int id);

    //查询无效座位
    List<InvalidSeat> selectInvalidSeats(int id);

    //添加无效座位
    void addInvalidSeats(List<InvalidSeat> invalidSeats);

    //删除无效座位
    void deleteInvalidSeats(int id);
}
