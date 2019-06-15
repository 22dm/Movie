package com.example.cinema.controller.user;

import com.example.cinema.blImpl.user.AccountServiceImpl;
import com.example.cinema.config.InterceptorConfiguration;
import com.example.cinema.vo.UserForm;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author huwen
 * @date 2019/3/23
 */
@RestController()
public class AccountController {
    @Autowired
    private AccountServiceImpl accountService;

    @PostMapping("/check")
    public ResponseVO login(@RequestBody UserForm userForm, HttpSession session){
        UserVO user = accountService.login(userForm);
        if(user == null){
           return ResponseVO.buildFailure("用户名或密码错误");
        }
        session.setAttribute(InterceptorConfiguration.SESSION_KEY, userForm);
        return ResponseVO.buildSuccess(user);
    }

    @PostMapping("/register")
    public ResponseVO registerAccount(@RequestBody UserForm userForm){
        return accountService.add(userForm);
    }

    @PostMapping("/logout")
    public String logOut(HttpSession session){
        session.removeAttribute(InterceptorConfiguration.SESSION_KEY);
        return "index";
    }

    @GetMapping("/manager/get")
    public ResponseVO getManager(){
        return accountService.getManager();
    }

    @PostMapping("/manager/add")
    public ResponseVO addManager(@RequestBody UserForm userForm){
        return accountService.addManager(userForm);
    }

    @PostMapping("/manager/edit")
    public ResponseVO editManager(@RequestBody UserForm userForm){
        return accountService.editManager(userForm);
    }

    @PostMapping("/manager/delete")
    public ResponseVO deleteManager(@RequestParam int userId){
        return accountService.deleteManager(userId);
    }
}
