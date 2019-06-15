package com.example.cinema.bl.promotion;

import com.example.cinema.vo.CardBuyForm;
import com.example.cinema.vo.CardChargeForm;
import com.example.cinema.vo.CardPromotionForm;
import com.example.cinema.vo.ResponseVO;

public interface VIPService {

    ResponseVO buy(CardBuyForm cardBuyForm);

    ResponseVO get(int userId);

    ResponseVO getPromotion();

    ResponseVO addPromotion(CardPromotionForm cardPromotionForm);

    ResponseVO deletePromotion(int target);

    ResponseVO charge(CardChargeForm cardChargeForm);
}
