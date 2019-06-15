package com.example.cinema.controller.promotion;

import com.example.cinema.bl.promotion.VIPService;
import com.example.cinema.vo.CardBuyForm;
import com.example.cinema.vo.CardChargeForm;
import com.example.cinema.vo.CardPromotionForm;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/vip")
public class VIPCardController {
    @Autowired
    VIPService vipService;

    @PostMapping("/buy")
    public ResponseVO buy(@RequestBody CardBuyForm cardBuyForm){
        return vipService.buy(cardBuyForm);
    }

    @GetMapping("/get")
    public ResponseVO get(@RequestParam int userId){
        return vipService.get(userId);
    }

    @GetMapping("/getPromotion")
    public ResponseVO getPromotion(){
        return vipService.getPromotion();
    }

    @PostMapping("/addPromotion")
    public ResponseVO addPromotion(@RequestBody CardPromotionForm cardPromotionForm){
        return vipService.addPromotion(cardPromotionForm);
    }

    @PostMapping("/deletePromotion")
    public ResponseVO deletePromotion(@RequestParam int target){
        return vipService.deletePromotion(target);
    }

    @PostMapping("/charge")
    public ResponseVO charge(@RequestBody CardChargeForm cardChargeForm){
        return vipService.charge(cardChargeForm);
    }
}
