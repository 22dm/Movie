package com.example.cinema.blImpl.promotion;

import com.example.cinema.vo.CouponVO;

public interface CouponServiceForBl {

    //获得VO
    CouponVO getById(int id);

    //为用户添加优惠券
    void addUserCoupon(int couponId, int userId);

    //删除用户的优惠券
    void deleteUserCoupon(int couponId, int userId);
}
