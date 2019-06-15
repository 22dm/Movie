package com.example.cinema.controller.management;

import com.example.cinema.bl.management.HallService;
import com.example.cinema.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hall")
public class HallController {
    @Autowired
    private HallService hallService;

    //获得所有影厅
    @GetMapping("/getAll")
    public ResponseVO getAll(){
        return hallService.getAll();
    }

    //获得指定影厅
    @GetMapping("/get")
    public ResponseVO get(@RequestParam int id){
        return hallService.get(id);
    }

    //添加影厅
    @PostMapping("/add")
    public ResponseVO add(@RequestBody HallForm hallForm){
        return hallService.add(hallForm);
    }

    //修改影厅
    @PostMapping("/edit")
    public ResponseVO edit(@RequestBody HallForm hallForm){
        return hallService.edit(hallForm);
    }

    //删除影厅
    @PostMapping("/delete")
    public ResponseVO delete(@RequestParam int id){
        return hallService.delete(id);
    }
}
