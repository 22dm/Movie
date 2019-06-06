package com.example.cinema.vo;

public class GiftForm {

    //优惠券ID
    int couponId;

    //用户ID列表
    int[] userIds;

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    public int[] getUserIds() {
        return userIds;
    }

    public void setUserIds(int[] userIds) {
        this.userIds = userIds;
    }
}
