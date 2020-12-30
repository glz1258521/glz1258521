package com.baizhi.service;

import com.baizhi.entity.Users;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public interface UsersService {
    HashMap<String,Object> queryUserPage(Integer page, Integer rows);

    String add(Users users);

    void uploadUserCover(MultipartFile headImg, String id, HttpServletRequest request);

    String edit(Users users);

    void del(Users users);
}
