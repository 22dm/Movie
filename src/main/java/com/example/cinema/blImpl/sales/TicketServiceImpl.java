package com.example.cinema.blImpl.sales;

import com.example.cinema.bl.sales.TicketService;
import com.example.cinema.blImpl.management.hall.HallServiceForBl;
import com.example.cinema.blImpl.management.schedule.ScheduleServiceForBl;
import com.example.cinema.data.sales.TicketMapper;
import com.example.cinema.po.Hall;
import com.example.cinema.po.ScheduleItem;
import com.example.cinema.po.Ticket;
import com.example.cinema.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liying on 2019/4/16.
 */
@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketMapper ticketMapper;
    @Autowired
    ScheduleServiceForBl scheduleService;
    @Autowired
    HallServiceForBl hallService;

    @Override
    @Transactional
    public ResponseVO addTicket(TicketForm ticketForm) {
        try {
            List<SeatForm> seats = ticketForm.getSeats();
            int userId = ticketForm.getUserId();
            int scheduleId = ticketForm.getScheduleId();
            for(SeatForm seat : seats) {
                Ticket ticket = new Ticket();
                ticket.setUserId(userId);
                ticket.setScheduleId(scheduleId);
                ticket.setColumnIndex(seat.getColumnIndex());
                ticket.setRowIndex(seat.getRowIndex());
                ticket.setState(0);
                ticketMapper.insertTicket(ticket);
            }
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    @Transactional
    public ResponseVO completeTicket(List<Integer> id, int couponId) {
        try {
            for(int ticketId : id) {
                ticketMapper.updateTicketState(ticketId, 1);
            }
            //couponMapper.deleteCouponUser();
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getBySchedule(int scheduleId) {
        try {
            List<Ticket> tickets = ticketMapper.selectTicketsBySchedule(scheduleId);
            ScheduleItem schedule=scheduleService.getScheduleItemById(scheduleId);
            Hall hall=hallService.getHallById(schedule.getHallId());
            int[][] seats=new int[hall.getRow()][hall.getColumn()];
            tickets.stream().forEach(ticket -> {
                seats[ticket.getRowIndex()][ticket.getColumnIndex()]=1;
            });
            ScheduleWithSeatVO scheduleWithSeatVO=new ScheduleWithSeatVO();
            scheduleWithSeatVO.setScheduleItem(schedule);
            scheduleWithSeatVO.setSeats(seats);
            return ResponseVO.buildSuccess(scheduleWithSeatVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getTicketByUser(int userId) {
        try {
            List<Ticket> tickets = ticketMapper.selectTicketByUser(userId);
            List<TicketWithScheduleVO> ticketWithScheduleVOs = new ArrayList<>();
            for(Ticket ticket : tickets) {
                TicketWithScheduleVO ticketWithScheduleVO = new TicketWithScheduleVO();
                ticketWithScheduleVO.setId(ticket.getId());
                ticketWithScheduleVO.setUserId(ticket.getUserId());
                ticketWithScheduleVO.setColumnIndex(ticket.getColumnIndex());
                ticketWithScheduleVO.setRowIndex(ticket.getRowIndex());
                ticketWithScheduleVO.setState(ticket.getVO().getState());
                ticketWithScheduleVO.setTime(ticket.getTime());
                ticketWithScheduleVO.setSchedule(scheduleService.getScheduleItemById(ticket.getScheduleId()));
                ticketWithScheduleVOs.add(ticketWithScheduleVO);
            }
            return ResponseVO.buildSuccess(ticketWithScheduleVOs);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    @Transactional
    public ResponseVO completeByVIPCard(List<Integer> id, int couponId) {
        try {
            for(int ticketId : id) {
                ticketMapper.updateTicketState(ticketId, 1);
            }
            //vipCardMapper.updateCardBalance();
            //couponMapper.deleteCouponUser();
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO cancelTicket(List<Integer> id) {
        return null;
    }



}
