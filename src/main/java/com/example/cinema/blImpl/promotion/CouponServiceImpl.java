package com.example.cinema.blImpl.promotion;

import com.example.cinema.bl.promotion.CouponService;
import com.example.cinema.data.promotion.CouponMapper;
import com.example.cinema.po.Coupon;
import com.example.cinema.vo.CouponForm;
import com.example.cinema.vo.CouponVO;
import com.example.cinema.vo.GiftForm;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liying on 2019/4/17.
 */
@Service
public class CouponServiceImpl implements CouponService, CouponServiceForBl {

    @Autowired
    CouponMapper couponMapper;

    @Override
    public ResponseVO getByUserId(int userId) {
        try {
            List<Coupon> coupons = couponMapper.selectCouponByUserId(userId);
            return ResponseVO.buildSuccess(CouponListToVo(coupons));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public CouponVO getById(int id) {
        try {
            return new CouponVO(couponMapper.selectCouponById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ResponseVO getByOrderId(int orderId) {
        try {
            List<Coupon> coupons = couponMapper.selectCouponByOrderId(orderId);
            return ResponseVO.buildSuccess(CouponListToVo(coupons));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getAll() {
        try {
            List<Coupon> coupons = couponMapper.selectAllCoupon();
            return ResponseVO.buildSuccess(CouponListToVo(coupons));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    private List<CouponVO> CouponListToVo(List<Coupon> coupons){
        List<CouponVO> couponVOS = new ArrayList<>();
        for(Coupon coupon:coupons){
            couponVOS.add(new CouponVO(coupon));
        }
        return couponVOS;
    }

    @Override
    public ResponseVO add(CouponForm couponForm) {
        try {
            if(!couponForm.getStartTime().before(couponForm.getEndTime())){
                return ResponseVO.buildFailure("结束时间不得早于起始时间");
            }
            Coupon coupon=new Coupon(couponForm);
            couponMapper.insertCoupon(coupon);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("未知错误");
        }
    }

    @Override
    public ResponseVO gift(GiftForm giftForm) {
        try {
            int couponId = giftForm.getCouponId();
            for(int userId: giftForm.getUserIds()) {
                addUserCoupon(couponId, userId);
            }
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public void addUserCoupon(int couponId, int userId){
        couponMapper.insertCouponUser(couponId, userId);
    }

    @Override
    public void deleteUserCoupon(int couponId, int userId){
        couponMapper.deleteCouponUser(couponId, userId);
    }
}
