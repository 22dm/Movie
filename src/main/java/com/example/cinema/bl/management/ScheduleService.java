package com.example.cinema.bl.management;

import com.example.cinema.vo.*;

public interface ScheduleService {

    //添加排片
    ResponseVO add(ScheduleForm scheduleForm);

    //修改排片
    ResponseVO edit(ScheduleForm scheduleForm);

    //删除排片
    ResponseVO delete(int id);

    //获得排片
    ResponseVO get(int id);

    //获得影厅的排片
    ResponseVO getByHallId(int hallId);

    //获得用户可见的电影排片
    ResponseVO userGet(int movieId);

    //设置可见时间
    ResponseVO setView(int view);

    //获得可见时间
    ResponseVO getView();
}
