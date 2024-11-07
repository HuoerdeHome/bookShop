package com.briup.springbootwebservelet.mapper;

import com.briup.demo.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 14409
 * 映射接口
 * //业务处理逻辑
 */
public interface UserMapper {
    //登录
    //根据用户名查询休息
    User selectByUsername(@Param("username") String username);

    //新增用户信息
    int insert(User user);

    //条件查询
    //多条件查询
    List<User> list(@Param("username") String username,
                    @Param("maxAge") Integer maxAge,
                    @Param("minAge") Integer minAge,
                    @Param("gender") String gender);

    int delete(Integer id);
}
