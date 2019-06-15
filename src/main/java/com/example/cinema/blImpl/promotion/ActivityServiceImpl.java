package com.example.cinema.blImpl.promotion;

import com.example.cinema.bl.promotion.ActivityService;
import com.example.cinema.blImpl.management.movie.MovieServiceForBl;
import com.example.cinema.data.promotion.ActivityMapper;
import com.example.cinema.po.Activity;
import com.example.cinema.po.ActivityMovie;
import com.example.cinema.vo.ActivityForm;
import com.example.cinema.vo.ActivityVO;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    ActivityMapper activityMapper;
    @Autowired
    CouponServiceForBl couponServiceForBl;
    @Autowired
    MovieServiceForBl movieServiceForBl;

    @Override
    @Transactional
    public ResponseVO add(ActivityForm activityForm) {
        try {
            Activity activity = new Activity(activityForm);
            activityMapper.insertActivity(activity);
            int activityId = activity.getId();
            System.out.println(activityId);
            List<Integer> movieIds = activityForm.getMovies();
            if(!movieIds.isEmpty()){
                List<ActivityMovie> activityMovies = new ArrayList<>();
                for(int movieId : movieIds){
                    ActivityMovie activityMovie = new ActivityMovie();
                    activityMovie.setActivityId(activityId);
                    activityMovie.setMovieId(movieId);
                    activityMovies.add(activityMovie);
                }
                activityMapper.insertActivityMovie(activityMovies);
            }
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("创建优惠活动失败");
        }
    }

    @Override
    public ResponseVO getAll() {
        try {
            List<Activity> activities = activityMapper.selectAllActivity();
            List<ActivityVO> activityVOS = new ArrayList<>();
            for(Activity activity: activities){
                ActivityVO activityVO = new ActivityVO(activity);
                List<Integer> movieIds = activityMapper.selectActivityMovie(activity.getId());
                List<String> movieNames = new ArrayList<>();
                for(int movieId : movieIds){
                    movieNames.add(movieServiceForBl.getVO(movieId).getName());
                }
                activityVO.setMovies(movieNames);
                activityVO.setCoupon(couponServiceForBl.getById(activity.getCouponId()));
                activityVOS.add(activityVO);
            }
            return ResponseVO.buildSuccess(activityVOS);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

}
