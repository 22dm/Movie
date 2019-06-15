package com.example.cinema.data.management;


import com.example.cinema.po.Schedule;
import com.example.cinema.vo.ScheduleForm;
import com.example.cinema.vo.ViewForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author fjj
 * @date 2019/4/11 4:18 PM
 */
@Mapper
public interface ScheduleMapper {

    void insert(Schedule schedule);

    void update(Schedule schedule);

    void delete(int id);

    Schedule select(int id);

    List<Schedule> selectByHallId(int hallId);

    List<Schedule> userGet(int movieId);

    void setView(int view);

    int getView();

    int ifConflict(Schedule schedule);
}
