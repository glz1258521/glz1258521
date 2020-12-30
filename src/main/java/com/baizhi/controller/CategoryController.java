package com.baizhi.controller;

import com.baizhi.entity.Category;

import com.baizhi.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;


/**
 * @author Category
 * @time 2020/12/22-16:54
 */
@Controller
@RequestMapping("/category")
public class CategoryController {
    @Resource
    CategoryService categoryService;

    @RequestMapping("/csy")
    @ResponseBody
    public HashMap<String, Object> queryUserPage(Integer page, Integer rows){
        return categoryService.queryUserPage(page,rows);
    }

    @RequestMapping("/csy2")
    @ResponseBody
    public HashMap<String, Object> query(Integer page, Integer rows,String parent_id){
        return categoryService.query(page,rows,parent_id);
    }

    @RequestMapping("/edit")
    @ResponseBody
    public void edit(Category category, String oper,String parent_id){

        if(oper.equals("add")){
            categoryService.add(category,parent_id);
        }
        if(oper.equals("edit")){
            categoryService.edit(category);
        }
        if(oper.equals("del")){
            categoryService.del(category);
        }

    }
}
