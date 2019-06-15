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

    private final static String GET_ALL_FAILED = "获取全部影厅信息失败";
    private final static String GET_FAILED = "获取影厅信息失败";
    private final static String ADD_FAILED = "添加影厅失败";
    private final static String EDIT_FAILED = "修改影厅失败";
    private final static String DELETE_FAILED = "删除影厅失败";

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
            return ResponseVO.buildFailure(GET_ALL_FAILED);
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
            return ResponseVO.buildFailure(GET_FAILED);
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
            return ResponseVO.buildFailure(ADD_FAILED);
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
                return ResponseVO.buildFailure(EDIT_FAILED);
            hallMapper.updateHall(hall);
            hallMapper.deleteInvalidSeats(id);
            hallMapper.addInvalidSeats(invalidSeats);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure(EDIT_FAILED);
        }
    }

    //座位状态VO转PO
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
                return ResponseVO.buildFailure(DELETE_FAILED);
            hallMapper.deleteHall(id);
            hallMapper.deleteInvalidSeats(id);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure(DELETE_FAILED);
        }
    }

    private boolean canEditHall(int id){
        return  true;
    }

    private boolean canDeleteHall(int id){
        return  true;
    }
}
