package com.example.cinema.data.management;

import com.example.cinema.po.Movie;
import com.example.cinema.po.MovieLike;
import com.example.cinema.vo.MovieForm;
import com.example.cinema.vo.MovieVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MovieMapper {

    void insert(Movie movie);

    void update(Movie movie);

    void delete(int id);

    List<Movie> selectAll();

    Movie selectById(int id);

    List<Movie> selectOn();

    List<Movie> selectOff();

    List<Movie> selectPre();

    List<Movie> selectOnAndPre();

    void like(int movieId, int userId);

    void unlike(int movieId, int userId);

    int ifLike(int movieId, int userId);

    int likeCount(int movieId);

    List<Movie> allLikeCount();

    List<Movie> search(String keyword);
}
