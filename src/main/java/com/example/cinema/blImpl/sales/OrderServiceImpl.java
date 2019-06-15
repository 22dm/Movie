package com.example.cinema.blImpl.sales;

import com.example.cinema.bl.sales.OrderService;
import com.example.cinema.blImpl.management.hall.HallServiceForBl;
import com.example.cinema.blImpl.management.schedule.ScheduleServiceForBl;
import com.example.cinema.blImpl.promotion.CouponServiceForBl;
import com.example.cinema.blImpl.promotion.VIPServiceForBl;
import com.example.cinema.data.sales.OrderMapper;
import com.example.cinema.po.*;
import com.example.cinema.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;
    @Autowired
    ScheduleServiceForBl scheduleService;
    @Autowired
    HallServiceForBl hallService;
    @Autowired
    VIPServiceForBl vipService;
    @Autowired
    CouponServiceForBl couponService;

    private final static String FAILED = "失败";
    private final static String AMOUNT_FAILED = "余额不足";

    @Override
    @Transactional
    public ResponseVO add(OrderForm orderForm) {
        try {
            Order order = new Order();
            order.setScheduleId(orderForm.getScheduleId());
            order.setUserId(orderForm.getUserId());
            order.setPrice(orderForm.getPrice());
            order.setStatus(0);
            orderMapper.addOrder(order);
            int orderId = order.getId();
            List<SeatVO> seats = orderForm.getSeats();
            for(SeatVO seatVO : seats) {
                Seat seat = new Seat();
                seat.setOrderId(orderId);
                seat.setColumnIndex(seatVO.getColumnIndex());
                seat.setRowIndex(seatVO.getRowIndex());
                orderMapper.addSeat(seat);
            }
            return ResponseVO.buildSuccess(orderId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure(FAILED);
        }
    }

    @Override
    @Transactional
    public ResponseVO pay(OrderPayForm orderPayForm) {
        try {
            OrderPay orderPay = new OrderPay(orderPayForm);
            int userId = orderMapper.selectOrdersById(orderPayForm.getOrderId()).getUserId();
            if(orderPayForm.getPayForm().getMention() == 1){
                if(vipService.getVO(userId).getBalance() < orderPayForm.getPayForm().getAmount()){
                    return ResponseVO.buildFailure(AMOUNT_FAILED);
                }
                vipService.addBalance(orderPayForm.getPayForm().getCardNumber(), -orderPayForm.getPayForm().getAmount());
            }
            couponService.deleteUserCoupon(orderPayForm.getCouponId(), userId);
            orderMapper.payOrder(orderPay);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure(FAILED);
        }
    }

    @Override
    public ResponseVO getBySchedule(int scheduleId) {
        try {
            List<Seat> soldSeats = orderMapper.selectSeatsBySchedule(scheduleId);
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
            return ResponseVO.buildFailure(FAILED);
        }
    }

    @Override
    public ResponseVO getByUserId(int userId) {
        try {
            List<Order> orders = orderMapper.selectOrdersByUserId(userId);
            List<OrderVO> orderVOS = new ArrayList<>();
            for(Order order : orders) {
                orderVOS.add(orderToVO(order));
            }
            return ResponseVO.buildSuccess(orderVOS);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure(FAILED);
        }
    }

    @Override
    public ResponseVO get(int id) {
        try {
            Order order = orderMapper.selectOrdersById(id);
            return ResponseVO.buildSuccess(orderToVO(order));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure(FAILED);
        }
    }

    private OrderVO orderToVO(Order order){
        OrderVO orderVO = new OrderVO(order);

        ScheduleVO scheduleVO = scheduleService.getVO(order.getScheduleId());
        orderVO.setSchedule(scheduleVO);

        List<Seat> seats = orderMapper.selectSeatsByOrderId(order.getId());
        List<SeatVO> seatVOS = new ArrayList<>();
        for(Seat seat : seats) {
            seatVOS.add(new SeatVO(seat));
        }
        orderVO.setSeats(seatVOS);
        return orderVO;
    }

    @Override
    public ResponseVO refundInfo(int orderId){
        try {
            List<Refund> refunds = orderMapper.selectRefund();
            Order order = orderMapper.selectOrdersById(orderId);
            long startTime = scheduleService.getVO(order.getScheduleId()).getStartTime().getTime();
            long now = new Date().getTime();
            int restHours = (int)((startTime - now) / 60 * 60 * 1000);
            double get = 0;
            if(refunds.size() == 0 || refunds.get(0).getHours() <= restHours){
                get = 1;
            }
            for (Refund refund : refunds) {
                if (restHours < refund.getHours()) {
                    get = refund.getGet();
                }
            }
            int refundPrice = (int) (order.getPrice() * get);
            return ResponseVO.buildSuccess(refundPrice);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure(FAILED);
        }
    }

    @Override
    public ResponseVO refund(int orderId){
        try {
            Order order = orderMapper.selectOrdersById(orderId);
            int refundPrice = (int)refundInfo(orderId).getContent();
            if(order.getMethod() == 1){
                vipService.addBalance(vipService.getVO(order.getUserId()).getId(), refundPrice);
            }
            orderMapper.refund(orderId);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure(FAILED);
        }
    }

    @Override
    public ResponseVO addRefund(RefundForm refundForm){
        try {
            orderMapper.insertRefund(new Refund(refundForm));
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure(FAILED);
        }
    }

    @Override
    public ResponseVO getRefund(){
        try {
            List<Refund> refunds = orderMapper.selectRefund();
            List<RefundVO> refundVOS = new ArrayList<>();
            for(Refund refund : refunds) {
                refundVOS.add(new RefundVO(refund));
            }
            return ResponseVO.buildSuccess(refundVOS);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure(FAILED);
        }
    }

    @Override
    public ResponseVO deleteRefund(int orderId){
        try {
            orderMapper.deleteRefund(orderId);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure(FAILED);
        }
    }
}
