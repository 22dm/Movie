package com.example.cinema.po;

import com.example.cinema.vo.UserForm;

public class User {

    //用户 ID
    private Integer id;

    //用户名
    private String username;

    //密码
    private String password;

    //角色
    private int role;

    public User(){

    }

    public User(UserForm userForm){
        id = userForm.getId();
        username = userForm.getUsername();
        password = userForm.getPassword();
        role = userForm.getRole();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
