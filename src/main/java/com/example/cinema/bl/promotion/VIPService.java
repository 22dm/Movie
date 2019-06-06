package com.example.cinema.bl.promotion;

import com.example.cinema.vo.CardBuyForm;
import com.example.cinema.vo.CardChargeForm;
import com.example.cinema.vo.CardPromotionForm;
import com.example.cinema.vo.ResponseVO;

public interface VIPService {

    //购买会员卡
    ResponseVO buy(CardBuyForm cardBuyForm);

    //获得用户的会员卡
    ResponseVO get(int userId);

    //获得所有会员卡优惠策略
    ResponseVO getPromotion();

    //添加会员卡优惠策略
    ResponseVO addPromotion(CardPromotionForm cardPromotionForm);

    //删除会员卡优惠策略
    ResponseVO deletePromotion(int target);

    //充值
    ResponseVO charge(CardChargeForm cardChargeForm);
}
