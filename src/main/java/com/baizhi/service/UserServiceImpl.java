package com.baizhi.service;

import com.baizhi.dao.UserMapper;
import com.baizhi.entity.User;
import com.baizhi.entity.UserExample;
import com.baizhi.util.UUIDUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author UserServiceImpl
 * @time 2020/12/22-9:39
 */

@Transactional
@Service
@Component
public class UserServiceImpl implements UserService{
    @Resource
    UserMapper userMapper;
    @Override
    public HashMap<String, Object> queryUserPage(Integer page, Integer rows) {
        // Integer page, Integer rows(每页展示条数)
        //返回  page=当前页   rows=[User,User]数据    tolal=总页数   records=总条数
        HashMap<String, Object> map = new HashMap<>();

        //设置当前页
        map.put("page",page);
        //创建条件对象
        UserExample example = new UserExample();
        //创建分页对象   参数：从第几条开始，展示几条
        RowBounds rowBounds = new RowBounds((page-1)*rows,rows);
        //查询数据
        List<User> users = userMapper.selectByExampleAndRowBounds(example, rowBounds);
        map.put("rows",users);

        //查询总条数
        int records = userMapper.selectCountByExample(example);
        map.put("records",records);

        //计算总页数
        Integer tolal=records%rows==0?records/rows:records/rows+1;
        map.put("total",tolal);

        return map;
    }
    @Override
    public String add(User user) {
        String uuid= UUIDUtil.getUUID();
        user.setId(uuid);
        user.setStatus("1");
        userMapper.insertSelective(user);
        return uuid;
    }
    @Override
    public void edit(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }
    @Override
    public void del(User user) {
        userMapper.deleteByPrimaryKey(user);
    }
}
