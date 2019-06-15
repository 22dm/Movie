package com.example.cinema.blImpl.promotion;

import com.example.cinema.bl.promotion.VIPService;
import com.example.cinema.data.promotion.VIPCardMapper;
import com.example.cinema.po.CardCharge;
import com.example.cinema.po.CardPromotion;
import com.example.cinema.po.VIPCard;
import com.example.cinema.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VIPServiceImpl implements VIPService {
    @Autowired
    VIPCardMapper vipCardMapper;

    @Override
    public ResponseVO buy(CardBuyForm cardBuyForm) {
        try {
            VIPCard vipCard = new VIPCard(cardBuyForm);
            vipCardMapper.insert(vipCard);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO get(int userId) {
        try {
            List<CardPromotion> cardPromotions = vipCardMapper.selectPromotion();
            List<CardPromotionVO> cardPromotionVOS = new ArrayList<>();
            for(CardPromotion cardPromotion: cardPromotions){
                cardPromotionVOS.add(new CardPromotionVO(cardPromotion));
            }

            VIPCard vipCard = vipCardMapper.select(userId);
            if(vipCard == null){
                VIPCardVO vipCardVO = new VIPCardVO();
                vipCardVO.setPromotions(cardPromotionVOS);
                return ResponseVO.buildSuccess(vipCardVO);
            }

            VIPCardVO vipCardVO = new VIPCardVO(vipCard);
            List<CardCharge> cardCharges = vipCardMapper.selectCharge(vipCard.getId());
            List<CardChargeVO> cardChargeVOS = new ArrayList<>();
            for(CardCharge cardCharge: cardCharges){
                cardChargeVOS.add(new CardChargeVO(cardCharge));
            }
            vipCardVO.setPromotions(cardPromotionVOS);
            vipCardVO.setCharges(cardChargeVOS);
            return ResponseVO.buildSuccess(vipCardVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getPromotion() {
        try {
            List<CardPromotion> cardPromotions = vipCardMapper.selectPromotion();
            List<CardPromotionVO> cardPromotionVOS = new ArrayList<>();
            for (CardPromotion cardPromotion : cardPromotions) {
                cardPromotionVOS.add(new CardPromotionVO(cardPromotion));
            }
            return ResponseVO.buildSuccess(cardPromotionVOS);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO addPromotion(CardPromotionForm cardPromotionForm) {
        try {
            vipCardMapper.insertPromotion(new CardPromotion(cardPromotionForm));
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO deletePromotion(int target) {
        try {
            vipCardMapper.deletePromotion(target);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO charge(CardChargeForm cardChargeForm) {
        try {
            int amount = cardChargeForm.getPayForm().getAmount();
            int gift = vipCardMapper.selectGift(amount);
            CardCharge cardCharge = new CardCharge(cardChargeForm);
            cardCharge.setGift(gift);
            vipCardMapper.charge(cardCharge);
            vipCardMapper.addBalance(cardChargeForm.getCardId(), amount + gift);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }
}
