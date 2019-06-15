package com.example.cinema.controller.router;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {
    @RequestMapping(value = "/index")
    public String getIndex() {
        return "index";
    }

    @RequestMapping(value = "/login")
    public String getLogIn() {
        return "login";
    }

    @RequestMapping(value = "/signUp")
    public String getSignUp() {
        return "signUp";
    }

    @RequestMapping(value = "/admin/movie")
    public String getAdminMovieManage() {
        return "admin/movie";
    }

    @RequestMapping(value = "/admin/schedule")
    public String getAdminSessionManage() {
        return "admin/schedule";
    }

    @RequestMapping(value = "/admin/hall")
    public String getAdminCinemaManage() {
        return "admin/hall";
    }

    @RequestMapping(value = "/admin/activity")
    public String getAdminPromotionManage() {
        return "admin/activity";
    }

    @RequestMapping(value = "/admin/coupon")
    public String getAdminCouponManage() {
        return "admin/coupon";
    }

    @RequestMapping(value = "/admin/vip")
    public String getAdminPromotionVIPCard() {
        return "admin/vip";
    }

    @RequestMapping(value = "/admin/statistic")
    public String getAdminCinemaStatistic() {
        return "admin/statistic";
    }

    @RequestMapping(value = "/admin/manager")
    public String getAdminManager() {
        return "admin/manager";
    }

    @RequestMapping(value = "/admin/refund")
    public String getAdminRefund() {
        return "admin/refund";
    }

    @RequestMapping(value = "/admin/gift")
    public String getAdminGift() {
        return "admin/gift";
    }

    @RequestMapping(value = "/admin/movieDetail")
    public String getAdminMovieDetail(@RequestParam int id) { return "admin/movieDetail"; }

    @RequestMapping(value = "/user/pay")
    public String userOrderPay() {
        return "user/pay";
    }

    @RequestMapping(value = "/user/order")
    public String getUserBuy() {
        return "user/order";
    }

    @RequestMapping(value = "/user/coupon")
    public String getUserCoupon() {
        return "user/coupon";
    }

    @RequestMapping(value = "/user/selectSeats")
    public String getUserMovieBuy(@RequestParam int id) {
        return "user/selectSeats";
    }

    @RequestMapping(value = "/user/movieDetail")
    public String getUserMovie(@RequestParam int id) {
        return "user/movieDetail";
    }

    @RequestMapping(value = "/user/vip")
    public String getUserMember() {
        return "user/vip";
    }
}
