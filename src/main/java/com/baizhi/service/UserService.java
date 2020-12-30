package com.baizhi.service;

import com.baizhi.entity.User;

import java.util.HashMap;

public interface UserService {
    HashMap<String,Object> queryUserPage(Integer page, Integer rows);

    String add(User user);

    void edit(User user);

    void del(User user);
}
