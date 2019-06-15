package com.example.cinema.bl.management;

import com.example.cinema.vo.*;

public interface ScheduleService {

    ResponseVO add(ScheduleForm scheduleForm);

    ResponseVO edit(ScheduleForm scheduleForm);

    ResponseVO delete(int id);

    ResponseVO get(int id);

    ResponseVO getByHallId(int hallId);

    ResponseVO userGet(int movieId);

    ResponseVO setView(int view);

    ResponseVO getView();
}
