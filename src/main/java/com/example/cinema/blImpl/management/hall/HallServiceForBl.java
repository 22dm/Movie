package com.example.cinema.blImpl.management.hall;

import com.example.cinema.vo.HallVO;
import com.example.cinema.vo.HallWithSeatsStatusVO;

public interface HallServiceForBl {

    HallVO getVO(int id);

    HallWithSeatsStatusVO getSeatsVO(int id);
}
