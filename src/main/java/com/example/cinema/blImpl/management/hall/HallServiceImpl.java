package com.example.cinema.blImpl.management.hall;

import com.example.cinema.bl.management.HallService;
import com.example.cinema.data.management.HallMapper;
import com.example.cinema.po.Hall;
import com.example.cinema.po.InvalidSeat;
import com.example.cinema.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HallServiceImpl implements HallService, HallServiceForBl {
    @Autowired
    private HallMapper hallMapper;

    @Override
    public ResponseVO getAll() {
        try {
            List<Hall> halls = hallMapper.selectAllHall();
            List<HallWithSeatsStatusVO> hallWithSeatsStatusVOS = new ArrayList<>();
            for(Hall hall: halls){
                hallWithSeatsStatusVOS.add(getSeatsVO(hall.getId()));
            }
            return ResponseVO.buildSuccess(hallWithSeatsStatusVOS);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("获取全部影厅信息失败");
        }
    }

    @Override
    public HallVO getVO(int id){
        Hall hall = hallMapper.selectHall(id);
        return new HallVO(hall);
    }

    @Override
    public HallWithSeatsStatusVO getSeatsVO(int id){
        Hall hall = hallMapper.selectHall(id);
        HallWithSeatsStatusVO hallWithValidSeatsVO = new HallWithSeatsStatusVO(hall);
        List<InvalidSeat> invalidSeats = hallMapper.selectInvalidSeats(id);
        int[][] seats = new int[hall.getRow()][hall.getColumn()];
        for(InvalidSeat seat: invalidSeats){
            seats[seat.getRow()][seat.getColumn()] = -1;
        }
        hallWithValidSeatsVO.setSeats(seats);
        return hallWithValidSeatsVO;
    }

    @Override
    public ResponseVO get(int id) {
        try {
            return ResponseVO.buildSuccess(getSeatsVO(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("获取影厅信息失败");
        }
    }

    @Override
    public ResponseVO add(HallForm hallForm) {
        try {
            Hall hall = new Hall(hallForm);
            hallMapper.addHall(hall);
            SeatsStatusVO seatsStatusVO = hallForm.getSeats();
            List<InvalidSeat> invalidSeats = seatStatusVOToPO(seatsStatusVO, hall.getId());
            hallMapper.addInvalidSeats(invalidSeats);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("添加影厅失败");
        }
    }

    @Override
    public ResponseVO edit(HallForm hallForm) {
        try {
            Hall hall = new Hall(hallForm);
            int id = hall.getId();
            SeatsStatusVO seatsStatusVO = hallForm.getSeats();
            List<InvalidSeat> invalidSeats = seatStatusVOToPO(seatsStatusVO, id);
            if(!canEditHall(id))
                return ResponseVO.buildFailure("修改影厅失败");
            hallMapper.updateHall(hall);
            hallMapper.deleteInvalidSeats(id);
            hallMapper.addInvalidSeats(invalidSeats);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("修改影厅失败");
        }
    }

    private List<InvalidSeat> seatStatusVOToPO(SeatsStatusVO seatsStatusVO, int id) {
        List<InvalidSeat> invalidSeats = new ArrayList<>();
        int[][] status = seatsStatusVO.getStatus();
        int row = status.length;
        int column = status[0].length;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++){
                if(status[i][j] == -1){
                    InvalidSeat invalidSeat = new InvalidSeat();
                    invalidSeat.setRow(i);
                    invalidSeat.setColumn(j);
                    invalidSeat.setId(id);
                    invalidSeats.add(invalidSeat);
                }
            }
        }
        return invalidSeats;
    }

    @Override
    public ResponseVO delete(int id) {
        try {
            if(!canDeleteHall(id))
                return ResponseVO.buildFailure("删除影厅失败");
            hallMapper.deleteHall(id);
            hallMapper.deleteInvalidSeats(id);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("删除影厅失败");
        }
    }

    //TODO: 是否可以修改影厅
    private boolean canEditHall(int id){
        return  true;
    }

    //TODO: 是否可以删除影厅
    private boolean canDeleteHall(int id){
        return  true;
    }
}
