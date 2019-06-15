package com.example.cinema.data.promotion;

import com.example.cinema.po.CardCharge;
import com.example.cinema.po.CardPromotion;
import com.example.cinema.po.Refund;
import com.example.cinema.po.VIPCard;
import com.example.cinema.vo.CardPromotionForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VIPCardMapper {

    void insert(VIPCard vipCard);

    VIPCard select(int userId);

    List<CardPromotion> selectPromotion();

    int selectGift(int target);

    void insertPromotion(CardPromotion cardPromotion);

    void deletePromotion(int target);

    void charge(CardCharge cardCharge);

    List<CardCharge> selectCharge(int cardId);

    void addBalance(int cardId, int balance);
}
