package com.example.cinema.blImpl.management.hall;

import com.example.cinema.vo.HallVO;
import com.example.cinema.vo.HallWithSeatsStatusVO;

public interface HallServiceForBl {

    //获得VO
    HallVO getVO(int id);

    //获得VO以及座位信息
    HallWithSeatsStatusVO getSeatsVO(int id);
}
