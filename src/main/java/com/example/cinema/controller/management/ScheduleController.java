package com.example.cinema.controller.management;

import com.example.cinema.bl.management.ScheduleService;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.ScheduleForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @PostMapping(value = "/add")
    public ResponseVO add(@RequestBody ScheduleForm scheduleForm){
        return scheduleService.add(scheduleForm);
    }

    @PostMapping(value = "/edit")
    public ResponseVO edit(@RequestBody ScheduleForm scheduleForm){
        return scheduleService.edit(scheduleForm);
    }

    @PostMapping(value = "/delete")
    public ResponseVO delete(@RequestParam int id){
        return scheduleService.delete(id);
    }

    @GetMapping(value = "/get")
    public ResponseVO get(@RequestParam int id){
        return scheduleService.get(id);
    }

    @GetMapping(value = "/getByHallId")
    public ResponseVO getByHallId(@RequestParam int hallId){
        return scheduleService.getByHallId(hallId);
    }

    @GetMapping(value = "/userGet")
    public ResponseVO userGet(@RequestParam int movieId){
        return scheduleService.userGet(movieId);
    }

    @PostMapping(value = "/setView")
    public ResponseVO setView(@RequestParam int view){
        return scheduleService.setView(view);
    }

    @GetMapping(value = "/getView")
    public ResponseVO getView(){
        return scheduleService.getView();
    }
}
