package com.example.cinema.bl.user;

import com.example.cinema.vo.UserForm;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.UserVO;

/**
 * @author huwen
 * @date 2019/3/23
 */
public interface AccountService {

    /**
     * 注册账号
     * @return
     */
    public ResponseVO add(UserForm userForm);

    /**
     * 用户登录，登录成功会将用户信息保存再session中
     * @return
     */
    public UserVO login(UserForm userForm);

    //获得管理员
    public ResponseVO getManager();

    //添加管理员
    public ResponseVO addManager(UserForm userForm);

    //编辑管理员
    public ResponseVO editManager(UserForm userForm);

    //删除管理员
    public ResponseVO deleteManager(int userId);

}
