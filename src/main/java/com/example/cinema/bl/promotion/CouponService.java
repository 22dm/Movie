package com.example.cinema.bl.promotion;

import com.example.cinema.vo.CouponForm;
import com.example.cinema.vo.GiftForm;
import com.example.cinema.vo.ResponseVO;

public interface CouponService {

    //添加优惠券
    ResponseVO add(CouponForm couponForm);

    //获得所有优惠券
    ResponseVO getAll();

    //获得用户的优惠券
    ResponseVO getByUserId(int userId);

    //获得订单可用的优惠券
    ResponseVO getByOrderId(int orderId);

    //批量赠送优惠券
    ResponseVO gift(GiftForm giftForm);
}
