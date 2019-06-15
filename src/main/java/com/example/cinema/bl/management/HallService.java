package com.example.cinema.bl.management;

import com.example.cinema.vo.HallForm;
import com.example.cinema.vo.ResponseVO;

public interface HallService {

    //获得所有影厅
    ResponseVO getAll();

    //获得指定影厅
    ResponseVO get(int id);

    //添加影厅
    ResponseVO add(HallForm hallForm);

    //修改影厅
    ResponseVO edit(HallForm hallForm);

    //删除影厅
    ResponseVO delete(int id);
}
