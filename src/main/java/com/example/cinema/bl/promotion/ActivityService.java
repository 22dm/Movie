package com.example.cinema.bl.promotion;

import com.example.cinema.vo.ActivityForm;
import com.example.cinema.vo.ResponseVO;

public interface ActivityService {

    //添加活动
    ResponseVO add(ActivityForm activityForm);

    //获得所有活动
    ResponseVO getAll();
}
