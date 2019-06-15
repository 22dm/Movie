package com.example.cinema.bl.management;

import com.example.cinema.vo.MovieForm;
import com.example.cinema.vo.ResponseVO;

public interface MovieService {

    ResponseVO add(MovieForm movieForm);

    ResponseVO edit(MovieForm movieForm);

    ResponseVO delete(int id);

    ResponseVO getAll();

    ResponseVO get(int id);

    ResponseVO getByIdWithLike(int movieId,  int userId);

    ResponseVO getOn();

    ResponseVO getOff();

    ResponseVO getPre();

    ResponseVO getOnAndPre();

    ResponseVO like(int movieId, int userId);

    ResponseVO unlike(int movieId, int userId);

    ResponseVO likeCount(int id);

    ResponseVO allLikeCount();

    ResponseVO search(String keyword);
}
