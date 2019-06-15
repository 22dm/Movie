package com.example.cinema.controller.statistics;

import com.example.cinema.bl.statistics.StatisticsService;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    @Autowired
    private StatisticsService statisticsService;

    @GetMapping(value = "/scheduleRate")
    public ResponseVO getScheduleRateByDate(@RequestParam(required = false) Date date){
        //此处date为非必填参数，若不填则默认为当天
        return statisticsService.getScheduleRateByDate(date);
    }

    @GetMapping(value = "/boxOffice/total")
    public ResponseVO getTotalBoxOffice(){
        return statisticsService.getTotalBoxOffice();
    }

    @GetMapping(value = "/audience/price")
    public ResponseVO getAudiencePrice(){
        return statisticsService.getAudiencePriceSevenDays();
    }

    @GetMapping(value = "/PlacingRate")
    //public ResponseVO getMoviePlacingRateByDate(@RequestParam Date date){
    //    return statisticsService.getMoviePlacingRateByDate(date);
    //}
    public ResponseVO getMoviePlacingRateByDate(){
        return statisticsService.getMoviePlacingRateByDate();
    }

    @GetMapping(value = "/popular/movie")
    public ResponseVO getPopularMovies(@RequestParam int days, @RequestParam int movieNum){
        return statisticsService.getPopularMovies(days, movieNum);
    }

    @GetMapping(value = "/topUser")
    public ResponseVO getTopUser(@RequestParam int cost){
        return statisticsService.getTopUser(cost);
    }










}
