package com.example.cinema.controller.management;

import com.example.cinema.bl.promotion.VIPService;
import com.example.cinema.bl.sales.OrderService;
import com.example.cinema.blImpl.promotion.CouponServiceImpl;
import com.example.cinema.blImpl.promotion.VIPServiceImpl;
import com.example.cinema.blImpl.sales.OrderServiceImpl;
import com.example.cinema.controller.user.AccountController;
import com.example.cinema.vo.GiftForm;
import com.example.cinema.vo.HallForm;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.UserForm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    HallController hallController;

    @Autowired
    private CouponServiceImpl couponService;

    @Autowired
    private VIPServiceImpl vipService;

    @Autowired
    private OrderServiceImpl orderService;

    @Test
    public void getAllHall() {
        ResponseVO responseVO = hallController.getAll();
    }

    @Test
    public void getAllCoupon() {
        ResponseVO responseVO = couponService.getAll();
    }

    @Test
    public void getHall() {
        ResponseVO responseVO = couponService.getByUserId(1);
    }

    @Test
    public void getVIPPromotion() {
        ResponseVO responseVO = vipService.getPromotion();
    }

    @Test
    public void getRefund() {
        ResponseVO responseVO = orderService.getRefund();
    }

    @Test
    @Rollback
    public void addHall() {
        HallForm hallForm = new HallForm();
        hallForm.setName("测试");
        hallForm.setRow(5);
        hallForm.setColumn(6);
        ResponseVO responseVO = hallController.add(hallForm);
    }

    @Test
    @Rollback
    public void gift() {
        GiftForm giftForm = new GiftForm();
        giftForm.setCouponId(1);
        int[] userIds = {1, 2, 3, 4, 5};
        giftForm.setUserIds(userIds);
        couponService.gift(giftForm);
    }
}