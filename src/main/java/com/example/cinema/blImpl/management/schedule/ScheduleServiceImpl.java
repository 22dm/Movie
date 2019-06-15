package com.example.cinema.blImpl.management.schedule;

import com.example.cinema.bl.management.ScheduleService;
import com.example.cinema.blImpl.management.hall.HallServiceForBl;
import com.example.cinema.blImpl.management.movie.MovieServiceForBl;
import com.example.cinema.data.management.ScheduleMapper;
import com.example.cinema.po.Schedule;
import com.example.cinema.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author fjj
 * @date 2019/4/11 4:14 PM
 */
@Service
public class ScheduleServiceImpl implements ScheduleService, ScheduleServiceForBl {
    private static final String TIME_CONFLICT_ERROR_MESSAGE = "时间段冲突";
    private static final String BEFORE_START_DATE_ERROR_MESSAGE = "排片时间不能早于电影上映时间";
    private static final String VIEW_CONFLICT_ERROR_MESSAGE = "有排片信息已对观众可见，无法删除或修改";


    @Autowired
    private ScheduleMapper scheduleMapper;
    @Autowired
    private MovieServiceForBl movieServiceForBl;
    @Autowired
    private HallServiceForBl hallServiceForBl;


    @Override
    public ResponseVO add(ScheduleForm scheduleForm){
        try {
            if(!preCheck(scheduleForm)){
                return ResponseVO.buildFailure("失败");
            }
            scheduleMapper.insert(new Schedule(scheduleForm));
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO edit(ScheduleForm scheduleForm){
        try {
            if(!preCheck(scheduleForm)){
                return ResponseVO.buildFailure("失败");
            }
            scheduleMapper.update(new Schedule(scheduleForm));
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO delete(int id){
        try {
            if(!preCheck(id)){
                return ResponseVO.buildFailure("失败");
            }
            scheduleMapper.delete(id);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO get(int id){
        try {
            Schedule schedule = scheduleMapper.select(id);
            return ResponseVO.buildSuccess(scheduleToVO(schedule));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getByHallId(int hallId){
        try {
            List<Schedule> schedules = scheduleMapper.selectByHallId(hallId);
            return ResponseVO.buildSuccess(scheduleListToVO(schedules));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO userGet(int movieId){
        try {
            List<Schedule> schedules = scheduleMapper.userGet(movieId);
            return ResponseVO.buildSuccess(scheduleListToVO(schedules));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO setView(int view){
        try{
            scheduleMapper.setView(view);
            return ResponseVO.buildSuccess();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getView(){
        try{
            return ResponseVO.buildSuccess(scheduleMapper.getView());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ScheduleVO getVO(int id){
        return scheduleToVO(scheduleMapper.select(id));
    }

    //schedulePO转VO
    private ScheduleVO scheduleToVO(Schedule schedule){
            ScheduleVO scheduleVO = new ScheduleVO(schedule);
            scheduleVO.setHall(hallServiceForBl.getVO(schedule.getHallId()));
            scheduleVO.setMovie(movieServiceForBl.getVO(schedule.getMovieId()));
            Date endTime = new Date(scheduleVO.getStartTime().getTime() + scheduleVO.getMovie().getLength() * 60000);
            scheduleVO.setEndTime(endTime);
            return scheduleVO;
    }

    //schedulePO列表转VO列表
    private List<ScheduleVO> scheduleListToVO(List<Schedule> schedules){
        List<ScheduleVO> scheduleVOS = new ArrayList<>();
        for(Schedule schedule : schedules){
            scheduleVOS.add(scheduleToVO(schedule));
        }
        return scheduleVOS;
    }

    private boolean preCheck(ScheduleForm scheduleForm){
        return true;
    }

    private boolean preCheck(int id){
        return true;
    }
}
