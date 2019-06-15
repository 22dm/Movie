package com.example.cinema.controller.promotion;

import com.example.cinema.bl.promotion.ActivityService;
import com.example.cinema.vo.ActivityForm;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    ActivityService activityService;

    @GetMapping("/getAll")
    public ResponseVO getAllActivity(){
        return activityService.getAll();
    }

    @PostMapping("/add")
    public ResponseVO addActivity(@RequestBody ActivityForm activityForm){
        return activityService.add(activityForm);
    }
}
