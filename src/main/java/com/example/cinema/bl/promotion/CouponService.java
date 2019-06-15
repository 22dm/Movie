package com.example.cinema.bl.promotion;

import com.example.cinema.vo.CouponForm;
import com.example.cinema.vo.ResponseVO;

public interface CouponService {

    ResponseVO add(CouponForm couponForm);

    ResponseVO getAll();

    ResponseVO getByUserId(int userId);

    ResponseVO getByOrderId(int orderId);

    ResponseVO give(int couponId, int userId);
}
