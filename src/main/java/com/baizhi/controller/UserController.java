package com.baizhi.controller;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author UserController
 * @time 2020/12/22-9:41
 */
@Controller
@RequestMapping("/users")
public class UserController {
    @Resource
    UserService userService;

    @ResponseBody
    @RequestMapping("/usersxs")
    public HashMap<String, Object> queryUserPage(Integer page, Integer rows){
        return userService.queryUserPage(page,rows);
    }
    @ResponseBody
    @RequestMapping("/edit")
    public String  edit(User user, String oper){
        String id =null;
        if(oper.equals("add")){
            userService.add(user);
        }
        if(oper.equals("edit")){
            userService.edit(user);
        }
        if(oper.equals("del")){
            userService.del(user);
        }
        return id;
    }
}
