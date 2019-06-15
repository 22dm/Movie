package com.example.cinema.controller.sales;

import com.example.cinema.bl.sales.TicketService;
import com.example.cinema.vo.OrderPayForm;
import com.example.cinema.vo.RefundForm;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.OrderForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by liying on 2019/4/16.
 */
@RestController
@RequestMapping("/order")
public class TicketController {
    @Autowired
    TicketService ticketService;

    //创建订单
    @PostMapping("/creat")
    public ResponseVO creatOrder(@RequestBody OrderForm orderForm){
        return ticketService.add(orderForm);
    }

    //银行卡支付订单
    @PostMapping("/pay")
    public ResponseVO buyTicket(@RequestBody OrderPayForm orderPayForm){
        return ticketService.pay(orderPayForm);
    }

    //获取某用户的订单
    @GetMapping("/getByUserId")
    public ResponseVO getOrderByUserId(@RequestParam int userId){
        return ticketService.getByUserId(userId);
    }

    //获取某订单
    @GetMapping("/get")
    public ResponseVO get(@RequestParam int id){
        return ticketService.get(id);
    }

    //获得某排片中的座位信息
    @GetMapping("/getSeatsStatus")
    public ResponseVO getSoldSeats(@RequestParam int id){
        return ticketService.getBySchedule(id);
    }

    @PostMapping("/refund")
    public ResponseVO refund(@RequestParam int orderId){
        return ticketService.refund(orderId);
    }

    @GetMapping("/getRefund")
    public ResponseVO getRefund(){
        return ticketService.getRefund();
    }

    @PostMapping("/addRefund")
    public ResponseVO addRefund(@RequestBody RefundForm refundForm){
        return ticketService.addRefund(refundForm);
    }

    @PostMapping("/deleteRefund")
    public ResponseVO deleteRefund(@RequestParam int hours){
        return ticketService.deleteRefund(hours);
    }
}
