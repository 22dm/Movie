package com.example.cinema.data.promotion;

import com.example.cinema.po.Coupon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by liying on 2019/4/17.
 */
@Mapper
public interface CouponMapper {

    void insertCoupon(Coupon coupon);

    List<Coupon> selectCouponByUserId(int userId);

    List<Coupon> selectCouponByOrderId(int orderId);

    List<Coupon> selectAllCoupon();

    Coupon selectCouponById(int id);

    void insertCouponUser(int couponId, int userId);

    void deleteCouponUser(int couponId, int userId);
}
