package com.baizhi.service;

import com.baizhi.dao.UsersMapper;
import com.baizhi.entity.Users;
import com.baizhi.entity.UsersExample;
import com.baizhi.util.UUIDUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author UsersServiceImpl
 * @time 2020/12/21-18:32
 */
@Transactional
@Service
@Component
public class UsersServiceImpl implements UsersService{
    @Resource
    UsersMapper usersMapper;
    @Override
    public HashMap<String, Object> queryUserPage(Integer page, Integer rows) {
            // Integer page, Integer rows(每页展示条数)
            //返回  page=当前页   rows=[User,User]数据    tolal=总页数   records=总条数
            HashMap<String, Object> map = new HashMap<>();

            //设置当前页
            map.put("page",page);
            //创建条件对象
            UsersExample example = new UsersExample();
            //创建分页对象   参数：从第几条开始，展示几条
            RowBounds rowBounds = new RowBounds((page-1)*rows,rows);
            //查询数据
            List<Users> users = usersMapper.selectByExampleAndRowBounds(example, rowBounds);
             //遍历集合
            for (Users user : users) {
            //根据用户id查询学分  redis
            String id = user.getId();
            //查询学分并赋值
            user.setScore("88");
            }
            map.put("rows",users);

            //查询总条数
            int records = usersMapper.selectCountByExample(example);
            map.put("records",records);

            //计算总页数
            Integer tolal=records%rows==0?records/rows:records/rows+1;
            map.put("total",tolal);

            return map;
        }

    @Override
    public String add(Users users) {
        String uuid=UUIDUtil.getUUID();
        users.setId(uuid);
        users.setCreateDate(new Date());
        users.setStatus("1");
        usersMapper.insertSelective(users);
        return uuid;
    }

    @Override
    public String edit(Users users) {
        if (users.getHeadImg()==""){
            Users users1 = usersMapper.selectByPrimaryKey(users);
            users.setHeadImg(users.getHeadImg());
            usersMapper.updateByPrimaryKeySelective(users);
        }
        Users users1 = usersMapper.selectByPrimaryKey(users);
        File f = new File("src/main/webapp/upload/cover/"+users1.getHeadImg());
        f.delete();
        usersMapper.updateByPrimaryKeySelective(users);
        return users.getId();
    }
    @Override
    public void uploadUserCover(MultipartFile headImg, String id, HttpServletRequest request) {
        //1.获取文件名
        String filename = headImg.getOriginalFilename();
        //图片拼接时间戳
        String newName=new Date().getTime()+"-"+filename;

        //2.根据相对路径获取绝对路径
        String realPath = request.getServletContext().getRealPath("/upload/cover");

        //获取文件夹
        File file = new File(realPath);
        //判断文件夹是否存在
        if(!file.exists()){
            file.mkdirs();//创建文件夹
        }

        //3.文件上传
        try {
            headImg.transferTo(new File(realPath,newName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Users users = new Users();
        users.setId(id);
        users.setHeadImg(newName);
        System.out.println(users);
        //4.修改数据
        usersMapper.updateByPrimaryKeySelective(users);
    }

    @Override
    public void del(Users users) {
        usersMapper.deleteByPrimaryKey(users);
    }
}
