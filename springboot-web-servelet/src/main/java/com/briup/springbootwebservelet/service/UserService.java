package com.briup.springbootwebservelet.service;

import com.briup.demo.entity.User;
import com.briup.springbootwebservelet.exception.CustomeException;
import com.briup.springbootwebservelet.mapper.UserMapper;
import com.briup.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 14409
 * 业务层组件+用户相关
 */
@Component
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User login(String username, String password) throws CustomeException {
        // 调用Dao层根据用户名查询用户信息
        User record = userMapper.selectByUsername(username);
        // 如果查询结果为空，则说明用户名不存在
        if (record == null) {
            throw new CustomeException("用户名不存在！");
        }
        // 如果查询结果不为空，则说明用户名存在，继续判断密码是否正确
        if (!record.getPassword().equals(password)) {
            throw new CustomeException("密码错误！");
        }
        // 两个if判断都不满足，则表示登录成功
        return record;
    }

    public void register(User user) throws CustomeException {
        // 根据用户名查询数据，判断用户名是否重复
        User record = userMapper.selectByUsername(user.getUsername());
        if (record != null) {
            throw new CustomeException("该用户名已被占用！");
        }
        // 如果用户名未被占用，则执行插入
        int updated = userMapper.insert(user);
        if (updated != 1) {
            throw new CustomeException("服务器繁忙，请稍后重试！");
        }
    }


    public List<User> list(String username,
                           Integer minAge,
                           Integer maxAge,
                           String gender) {
        return userMapper.list(username, minAge, maxAge, gender);
    }

    public void delete(Integer id) throws CustomeException {
        int updated = userMapper.delete(id);
        if (updated != 1) {
            throw new CustomeException("删除失败，请稍后重试！");
        }
    }
}
