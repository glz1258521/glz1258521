package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import com.baizhi.service.AdminServiceImpl;
import com.baizhi.util.ImageCodeUtil;
import com.baizhi.util.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author UserController
 * @time 2020/12/18-19:21
 */
@Controller
@RequestMapping("/user")
public class AdminController {
    @Autowired
    AdminService b;


    @RequestMapping("/dl")
    public String loginUser(Admin user, HttpSession session) {
        Admin user1 = b.loginUser(user.getUsername(), user.getPassword());
        session.setAttribute("user1", user1);
        return "redirect:/main/main.jsp";
    }
    //获取验证码
    @RequestMapping("/getImageCode")
    public  String getImageCode(HttpSession session, HttpServletResponse response){
        ImageCodeUtil imageCodeUtil=new ImageCodeUtil();
        String securityCode = imageCodeUtil.getSecurityCode();
        System.out.println(securityCode);
        session.setAttribute("securityCode", securityCode);
        ServletOutputStream sos;
        try {
            sos = response.getOutputStream();
            ImageIO.write(imageCodeUtil.createImage(securityCode), "jpeg", sos);
            sos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping("/tc")
    public String tc(HttpSession session) {
        session.removeAttribute("user1");
        //session数据清空
        session.invalidate();
        return "redirect:/login/login.jsp";
    }
}
