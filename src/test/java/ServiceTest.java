import com.example.cinema.bl.promotion.CouponService;
import com.example.cinema.blImpl.management.hall.HallServiceImpl;
import com.example.cinema.blImpl.promotion.CouponServiceImpl;
import com.example.cinema.data.management.HallMapper;
import com.example.cinema.po.Hall;
import com.example.cinema.po.InvalidSeat;
import com.example.cinema.vo.GiftForm;
import com.example.cinema.vo.HallForm;
import com.example.cinema.vo.HallWithSeatsStatusVO;
import com.example.cinema.vo.ResponseVO;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class ServiceTest {

    @Mock
    private HallMapper hallMapper;

    @InjectMocks
    @Autowired
    private HallServiceImpl hallService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @org.junit.Test
    public void getAllHall() {
        List<Hall> halls = new ArrayList<>();
        Hall hall1 = new Hall();
        hall1.setId(1);
        hall1.setName("测试影厅1");
        hall1.setColumn(5);
        hall1.setRow(8);
        halls.add(hall1);

        Hall hall2 = new Hall();
        hall2.setId(2);
        hall2.setName("测试影厅2");
        hall2.setColumn(3);
        hall2.setRow(5);
        halls.add(hall2);

        List<InvalidSeat> invalidSeats1 = new ArrayList<>();
        InvalidSeat invalidSeat1_1 = new InvalidSeat();
        invalidSeat1_1.setId(1);
        invalidSeat1_1.setColumn(1);
        invalidSeat1_1.setRow(1);
        invalidSeats1.add(invalidSeat1_1);

        List<InvalidSeat> invalidSeats2 = new ArrayList<>();
        InvalidSeat invalidSeat2_1 = new InvalidSeat();
        invalidSeat2_1.setId(2);
        invalidSeat2_1.setColumn(1);
        invalidSeat2_1.setRow(1);
        invalidSeats2.add(invalidSeat2_1);

        doReturn(halls).when(hallMapper).selectAllHall();
        doReturn(hall1).when(hallMapper).selectHall(1);
        doReturn(hall2).when(hallMapper).selectHall(2);
        doReturn(invalidSeats1).when(hallMapper).selectInvalidSeats(1);
        doReturn(invalidSeats2).when(hallMapper).selectInvalidSeats(2);

        List<HallWithSeatsStatusVO> hallWithSeatsStatusVOS = new ArrayList<>();
        HallWithSeatsStatusVO hallWithSeatsStatusVO1 = new HallWithSeatsStatusVO(hall1);
        int[][] seats1 = new int[8][5];
        seats1[1][1] = -1;
        hallWithSeatsStatusVO1.setSeats(seats1);
        hallWithSeatsStatusVOS.add(hallWithSeatsStatusVO1);

        HallWithSeatsStatusVO hallWithSeatsStatusVO2 = new HallWithSeatsStatusVO(hall2);
        int[][] seats2 = new int[5][3];
        seats2[1][1] = -1;
        hallWithSeatsStatusVO2.setSeats(seats2);
        hallWithSeatsStatusVOS.add(hallWithSeatsStatusVO2);

        ResponseVO responseVOExpected = ResponseVO.buildSuccess(hallWithSeatsStatusVOS);
        ResponseVO responseVO = hallService.getAll();

        assertEquals(responseVOExpected, responseVO);
    }

    @org.junit.Test
    public void getHall() {
        Hall hall1 = new Hall();
        hall1.setId(1);
        hall1.setName("测试影厅1");
        hall1.setColumn(5);
        hall1.setRow(8);

        List<InvalidSeat> invalidSeats1 = new ArrayList<>();
        InvalidSeat invalidSeat1_1 = new InvalidSeat();
        invalidSeat1_1.setId(1);
        invalidSeat1_1.setColumn(1);
        invalidSeat1_1.setRow(1);
        invalidSeats1.add(invalidSeat1_1);

        doReturn(hall1).when(hallMapper).selectHall(1);
        doReturn(invalidSeats1).when(hallMapper).selectInvalidSeats(1);

        HallWithSeatsStatusVO hallWithSeatsStatusVO1 = new HallWithSeatsStatusVO(hall1);
        int[][] seats1 = new int[8][5];
        seats1[1][1] = -1;
        hallWithSeatsStatusVO1.setSeats(seats1);


        ResponseVO responseVOExpected = ResponseVO.buildSuccess(hallWithSeatsStatusVO1);
        ResponseVO responseVO = hallService.get(1);

        assertEquals(responseVOExpected, responseVO);
    }

}