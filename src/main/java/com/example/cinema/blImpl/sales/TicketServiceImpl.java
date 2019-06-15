package com.example.cinema.blImpl.sales;

import com.example.cinema.bl.sales.TicketService;
import com.example.cinema.blImpl.management.hall.HallServiceForBl;
import com.example.cinema.blImpl.management.schedule.ScheduleServiceForBl;
import com.example.cinema.data.sales.TicketMapper;
import com.example.cinema.po.*;
import com.example.cinema.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    public ResponseVO add(OrderForm orderForm) {
        try {
            Order order = new Order();
            order.setScheduleId(orderForm.getScheduleId());
            order.setUserId(orderForm.getUserId());
            order.setPrice(orderForm.getPrice());
            order.setStatus(0);
            ticketMapper.addOrder(order);
            int orderId = order.getId();
            List<SeatVO> seats = orderForm.getSeats();
            for(SeatVO seatVO : seats) {
                Seat seat = new Seat();
                seat.setOrderId(orderId);
                seat.setColumnIndex(seatVO.getColumnIndex());
                seat.setRowIndex(seatVO.getRowIndex());
                ticketMapper.addSeat(seat);
            }
            return ResponseVO.buildSuccess(orderId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("创建订单失败");
        }
    }

    @Override
    @Transactional
    public ResponseVO pay(OrderPayForm orderPayForm) {
        try {
            //TODO:判断余额，扣款，扣优惠券，送优惠券
            OrderPay orderPay = new OrderPay(orderPayForm);
            ticketMapper.payOrder(orderPay);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("支付失败");
        }
    }

    @Override
    public ResponseVO getBySchedule(int scheduleId) {
        try {
            List<Seat> soldSeats = ticketMapper.selectSeatsBySchedule(scheduleId);
            ScheduleVO schedule=scheduleService.getVO(scheduleId);
            HallWithSeatsStatusVO hallWithSeatsStatusVO = hallService.getSeatsVO(schedule.getHall().getId());
            int[][] seats = hallWithSeatsStatusVO.getSeats();
            for(Seat seat : soldSeats){
                seats[seat.getRowIndex()][seat.getColumnIndex()] = 1;
            }
            ScheduleWithSeatVO scheduleWithSeatVO=new ScheduleWithSeatVO();
            scheduleWithSeatVO.setSchedule(schedule);
            scheduleWithSeatVO.setSeats(seats);
            return ResponseVO.buildSuccess(scheduleWithSeatVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getByUserId(int userId) {
        try {
            List<Order> orders = ticketMapper.selectOrdersByUserId(userId);
            List<OrderVO> orderVOS = new ArrayList<>();
            for(Order order : orders) {
                orderVOS.add(orderToVO(order));
            }
            return ResponseVO.buildSuccess(orderVOS);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO get(int id) {
        try {
            Order order = ticketMapper.selectOrdersById(id);
            return ResponseVO.buildSuccess(orderToVO(order));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    private OrderVO orderToVO(Order order){
        OrderVO orderVO = new OrderVO(order);

        ScheduleVO scheduleVO = scheduleService.getVO(order.getScheduleId());
        orderVO.setSchedule(scheduleVO);

        List<Seat> seats = ticketMapper.selectSeatsByOrderId(order.getId());
        List<SeatVO> seatVOS = new ArrayList<>();
        for(Seat seat : seats) {
            seatVOS.add(new SeatVO(seat));
        }
        orderVO.setSeats(seatVOS);
        return orderVO;
    }

    @Override
    public ResponseVO refund(int orderId){
        try {
            ticketMapper.refund(orderId);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO addRefund(RefundForm refundForm){
        try {
            ticketMapper.insertRefund(new Refund(refundForm));
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getRefund(){
        try {
            List<Refund> refunds = ticketMapper.selectRefund();
            List<RefundVO> refundVOS = new ArrayList<>();
            for(Refund refund : refunds) {
                refundVOS.add(new RefundVO(refund));
            }
            return ResponseVO.buildSuccess(refundVOS);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO deleteRefund(int orderId){
        try {
            ticketMapper.deleteRefund(orderId);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }
}
