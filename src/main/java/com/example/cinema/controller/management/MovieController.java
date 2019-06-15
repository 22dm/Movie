package com.example.cinema.controller.management;

import com.example.cinema.vo.MovieForm;
import com.example.cinema.bl.management.MovieService;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping(value = "/add")
    public ResponseVO add(@RequestBody MovieForm movieForm){
        return movieService.add(movieForm);
    }

    @PostMapping(value = "/edit")
    public ResponseVO edit(@RequestBody MovieForm movieForm){
        return movieService.edit(movieForm);
    }

    @PostMapping(value = "/delete")
    public ResponseVO delete(@RequestParam int id){
        return movieService.delete(id);
    }

    @GetMapping(value = "/getAll")
    public ResponseVO getAll(){
        return movieService.getAll();
    }

    @GetMapping(value = "/getOnAndPre")
    public ResponseVO getOnAndPre(){
        return movieService.getOnAndPre();
    }

    @GetMapping(value = "/getOn")
    public ResponseVO getOn(){
        return movieService.getOn();
    }

    @GetMapping(value = "/getOff")
    public ResponseVO getOff(){
        return movieService.getOff();
    }

    @GetMapping(value = "/getPre")
    public ResponseVO getPre(){
        return movieService.getPre();
    }

    @GetMapping(value = "/get")
    public ResponseVO get(@RequestParam int id){
        return movieService.get(id);
    }

    @GetMapping(value = "/getByIdWithLike")
    public ResponseVO getByIdWithLike(@RequestParam int movieId, @RequestParam int userId){
        return movieService.getByIdWithLike(movieId, userId);
    }

    @PostMapping(value = "/like")
    public ResponseVO like(@RequestParam int movieId, @RequestParam int userId){
        return movieService.like(movieId, userId);
    }

    @PostMapping(value = "/unlike")
    public ResponseVO unlike(@RequestParam int movieId, @RequestParam int userId){
        return movieService.unlike(movieId, userId);
    }

    @GetMapping(value = "/likeCount")
    public ResponseVO likeCount(@RequestParam int movieId){
        return movieService.likeCount(movieId);
    }

    @GetMapping(value = "/allLikeCount")
    public ResponseVO allLikeCount(){
        return movieService.allLikeCount();
    }

    @GetMapping(value = "/search")
    public ResponseVO search(@RequestParam String keyword){
        return movieService.search(keyword);
    }
}
