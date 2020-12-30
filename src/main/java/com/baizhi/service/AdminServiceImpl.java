package com.baizhi.service;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author UserServiceImpl
 * @time 2020/12/18-19:16
 */
@Service
@Component
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminDao a;

    public AdminDao getA() {
        return a;
    }

    public void setA(AdminDao a) {
        this.a = a;
    }

    @Override
    public Admin loginUser(String username, String password) {
        Admin user = a.loginUser(username);
        if (user == null) {
            throw new RuntimeException("用户名不存在");
        }
        ;
        if (user.getPassword().equals(password) == false) {
            throw new RuntimeException("密码错误");
        }
        ;
        return user;
    }
}