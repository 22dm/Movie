package com.example.cinema.blImpl.management.movie;

import com.example.cinema.bl.management.MovieService;
import com.example.cinema.blImpl.management.schedule.ScheduleServiceForBl;
import com.example.cinema.data.management.MovieMapper;
import com.example.cinema.po.Movie;
import com.example.cinema.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService, MovieServiceForBl {

    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private ScheduleServiceForBl scheduleServiceForBl;

    @Override
    public ResponseVO add(MovieForm movieForm){
        try {
            movieMapper.insert(new Movie(movieForm));
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO edit(MovieForm movieForm) {
        try {
            if(preCheck(movieForm.getId())){
                movieMapper.update(new Movie(movieForm));
                return ResponseVO.buildSuccess();
            }
            return ResponseVO.buildFailure("失败");
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO delete(int id){
        try {
            if(preCheck(id)){
                movieMapper.delete(id);
                return ResponseVO.buildSuccess();
            }
            return ResponseVO.buildFailure("失败");
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    private boolean preCheck(int id){
        return true;
    }

    @Override
    public ResponseVO getAll(){
        try {
            List<Movie> movies = movieMapper.selectAll();
            return ResponseVO.buildSuccess(movieList2MovieVOList(movies));
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO get(int id){
        try {
            Movie movie = movieMapper.selectById(id);
            return ResponseVO.buildSuccess(new MovieVO(movie));
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getByIdWithLike(int movieId, int userId){
        try {
            MovieLikeVO movieLikeVO = new MovieLikeVO();
            movieLikeVO.setName(movieMapper.selectById(movieId).getName());
            movieLikeVO.setCount(movieMapper.likeCount(movieId));
            movieLikeVO.setIsLike(movieMapper.ifLike(movieId, userId));
            return ResponseVO.buildSuccess(movieLikeVO);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getOn(){
        try {
            List<Movie> movies = movieMapper.selectOn();
            System.out.println(movies.size());
            return ResponseVO.buildSuccess(movieList2MovieVOList(movies));
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getOff(){
        try {
            List<Movie> movies = movieMapper.selectOff();
            System.out.println("OFF" + movies.size());
            return ResponseVO.buildSuccess(movieList2MovieVOList(movies));
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getPre(){
        try {
            List<Movie> movies = movieMapper.selectPre();
            System.out.println(movies.size());
            return ResponseVO.buildSuccess(movieList2MovieVOList(movies));
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getOnAndPre(){
        try {
            List<Movie> movies = movieMapper.selectOnAndPre();
            System.out.println(movies.size());
            return ResponseVO.buildSuccess(movieList2MovieVOList(movies));
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO like(int movieId, int userId){
        try {
            movieMapper.like(movieId, userId);
            return ResponseVO.buildSuccess();
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO unlike(int movieId,  int userId){
        try {
            movieMapper.unlike(movieId, userId);
            return ResponseVO.buildSuccess();
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO likeCount(int id){
        return ResponseVO.buildFailure("失败-TODO");
    }

    @Override
    public ResponseVO allLikeCount(){
        try {
            List<Movie> movies = movieMapper.allLikeCount();
            return ResponseVO.buildSuccess(movies);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO search(String keyword){
        try {
            return ResponseVO.buildSuccess(movieList2MovieVOList(movieMapper.search(keyword)));
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public MovieVO getVO(int id){
        return new MovieVO(movieMapper.selectById(id));
    }

    //moviePO列表转VO列表
    private List<MovieVO> movieList2MovieVOList(List<Movie> movies){
        List<MovieVO> movieVOList = new ArrayList<>();
        for(Movie movie : movies){
            movieVOList.add(new MovieVO(movie));
        }
        return movieVOList;
    }

}
