package com.example.cinema.controller.promotion;

import com.example.cinema.bl.promotion.CouponService;
import com.example.cinema.vo.CouponForm;
import com.example.cinema.vo.GiftForm;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coupon")
public class CouponController {

    @Autowired
    CouponService couponService;

    @PostMapping("/add")
    public ResponseVO addCoupon(@RequestBody CouponForm couponForm){
        return couponService.add(couponForm);
    }

    @GetMapping("/getAll")
    public ResponseVO getAllCoupon(){
        return couponService.getAll();
    }

    @GetMapping("/getByUserId")
    public ResponseVO getCouponsByUserId(@RequestParam int userId){
        return couponService.getByUserId(userId);
    }

    @GetMapping("/getByOrderId")
    public ResponseVO getCouponsByOrderId(@RequestParam int orderId){
        return couponService.getByOrderId(orderId);
    }

    @PostMapping("/gift")
    public ResponseVO getCouponsByOrderId(@RequestBody GiftForm giftForm){
        return couponService.gift(giftForm);
    }
}
