package com.example.cinema.blImpl.promotion;

import com.example.cinema.vo.VIPCardVO;

public interface VIPServiceForBl {

    //增加余额
    void addBalance(int cardId, int balance);

    //获得VO
    VIPCardVO getVO(int userId);
}
