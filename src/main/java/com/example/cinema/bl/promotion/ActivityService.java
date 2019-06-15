package com.example.cinema.bl.promotion;

import com.example.cinema.vo.ActivityForm;
import com.example.cinema.vo.ResponseVO;

public interface ActivityService {
    
    ResponseVO add(ActivityForm activityForm);

    ResponseVO getAll();
}
