package com.baizhi.controller;

import com.baizhi.entity.Users;
import com.baizhi.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @author UsersController
 * @time 2020/12/21-17:42
 */
@Controller
@RequestMapping("/user")
public class UsersController {
    @Resource
    UsersService usersService;
    @ResponseBody
    @RequestMapping("/userxs")
    public HashMap<String, Object> queryUserPage(Integer page, Integer rows){
      return usersService.queryUserPage(page,rows);
    }
    @ResponseBody
    @RequestMapping("/edit")
    public String edit(Users users,String oper){
        String id =null;
        if(oper.equals("add")){
           id= usersService.add(users);
        }
        if(oper.equals("edit")){
            id=usersService.edit(users);
        }
        if(oper.equals("del")){
            usersService.del(users);
        }
        return id;
    }
    @ResponseBody
    @RequestMapping("uploadUserCover")
    public void uploadUserCover(MultipartFile headImg, String id, HttpServletRequest request){
        usersService.uploadUserCover(headImg, id, request);
    }
}
