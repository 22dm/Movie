package com.example.cinema.data.promotion;

import com.example.cinema.po.Activity;
import com.example.cinema.po.ActivityMovie;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ActivityMapper {

    void insertActivity(Activity activity);

    List<Integer> selectActivityMovie(int activityId);

    void insertActivityMovie(List<ActivityMovie> activityMovies);

    List<Activity> selectAllActivity();

    List<Activity> selectMovieActivity(int movieId);
}
