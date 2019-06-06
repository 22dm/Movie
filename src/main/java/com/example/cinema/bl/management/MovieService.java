package com.example.cinema.bl.management;

import com.example.cinema.vo.MovieForm;
import com.example.cinema.vo.ResponseVO;

public interface MovieService {

    //上架电影
    ResponseVO add(MovieForm movieForm);

    //修改电影
    ResponseVO edit(MovieForm movieForm);

    //删除电影
    ResponseVO delete(int id);

    //获得全部电影
    ResponseVO getAll();

    //获得电影信息
    ResponseVO get(int id);

    //获得电影以及想看信息
    ResponseVO getByIdWithLike(int movieId,  int userId);

    //获得正在上映的电影
    ResponseVO getOn();

    //获得已下架电影
    ResponseVO getOff();

    //获得即将上映电影
    ResponseVO getPre();

    //获得正在上映或即将上映的电影
    ResponseVO getOnAndPre();

    //想看
    ResponseVO like(int movieId, int userId);

    //取消想看
    ResponseVO unlike(int movieId, int userId);

    //统计想看人数
    ResponseVO likeCount(int id);

    //获得所有电影想看人数排行
    ResponseVO allLikeCount();

    //搜索电影
    ResponseVO search(String keyword);
}
