package com.baizhi.service;

import com.baizhi.entity.Admin;

public interface AdminService {
    Admin loginUser(String username, String password);//登录
}