package com.baizhi.service;

import com.baizhi.entity.Category;

import java.util.HashMap;

public interface CategoryService {
    //查一级
    HashMap<String,Object> queryUserPage(Integer page, Integer rows);
    //查二级
    HashMap<String,Object> query(Integer page, Integer rows,String parent_id);
    void add(Category category,String parent_id);

    void edit(Category category);

    void del(Category category);
}
