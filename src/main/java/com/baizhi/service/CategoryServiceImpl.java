package com.baizhi.service;

import com.baizhi.annotation.AddLog;
import com.baizhi.dao.CategoryMapper;
import com.baizhi.entity.*;
import com.baizhi.util.UUIDUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * @author CategoryServiceImpl
 * @time 2020/12/22-16:45
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{
    @Resource
    CategoryMapper categoryMapper;
    @Override
    public HashMap<String, Object> queryUserPage(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();

        //设置当前页
        map.put("page",page);
        //创建条件对象
        CategoryExample example = new CategoryExample();
        //创建分页对象   参数：从第几条开始，展示几条
        example.createCriteria().andLevelsEqualTo(1);
        RowBounds rowBounds = new RowBounds((page-1)*rows,rows);
        //查询数据
        List<Category> users = categoryMapper.selectByExampleAndRowBounds(example, rowBounds);
        //遍历集合
        map.put("rows",users);

        //查询总条数
        int records = categoryMapper.selectCountByExample(example);
        map.put("records",records);

        //计算总页数
        Integer tolal=records%rows==0?records/rows:records/rows+1;
        map.put("total",tolal);

        return map;
    }
    @Override
    public HashMap<String, Object> query(Integer page, Integer rows,String parent_id) {
        HashMap<String, Object> map = new HashMap<>();

        //设置当前页
        map.put("page",page);
        //创建条件对象
        CategoryExample example = new CategoryExample();
        //创建分页对象   参数：从第几条开始，展示几条
        example.createCriteria().andParentIdEqualTo(parent_id);
        RowBounds rowBounds = new RowBounds((page-1)*rows,rows);
        //查询数据
        List<Category> users = categoryMapper.selectByExampleAndRowBounds(example, rowBounds);
        //遍历集合
        map.put("rows",users);

        //查询总条数
        int records = categoryMapper.selectCountByExample(example);
        map.put("records",records);

        //计算总页数
        Integer tolal=records%rows==0?records/rows:records/rows+1;
        map.put("total",tolal);

        return map;
    }

    @AddLog(value = "删除用户")
    @Override
    public void add(Category category,String parent_id) {

        if(parent_id!=null){
            category.setLevels(2);
            category.setParentId(parent_id);
        }else{
            category.setLevels(1);
        }
        String uuid= UUIDUtil.getUUID();
        category.setId(uuid);
        categoryMapper.insertSelective(category);
    }

    @AddLog(value = "删除用户")
    @Override
    public void edit(Category category) {
      categoryMapper.updateByPrimaryKeySelective(category);
    }

    @AddLog(value = "删除用户")
    @Override
    public void del(Category category) {
        categoryMapper.deleteByPrimaryKey(category);
    }
}
