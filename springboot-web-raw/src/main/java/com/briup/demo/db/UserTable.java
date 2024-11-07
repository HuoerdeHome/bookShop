package com.briup.demo.db;

import com.briup.demo.entity.User;

import java.util.*;

/**
 * 用一个Java类+多个User对象，充当一个数据库表
 *
 * 1、登录和查询，都是查询这里的数据
 * 2、注册，像这里添加对象即可
 * @author YuYan
 * @date 2024-10-28 17:03:39
 */
public class UserTable {

    private static Map<String, User> users;

    static {
        users = new HashMap<>();
        add(new User("Tom", "123", "male", 10, new Date(), "123@qq.com", "自我介绍", 0));
        add(new User("Jack", "123", "male", 13, new Date(), "123@qq.com", "自我介绍", 1));
        add(new User("Lucy", "123", "female", 20, new Date(), "123@qq.com", "自我介绍", 2));
        add(new User("Lily", "123", "female", 24, new Date(), "123@qq.com", "自我介绍", 3));
        add(new User("Sarah", "123", "female", 30, new Date(), "123@qq.com", "自我介绍", 4));
    }

    public static void add(User user) {
        users.put(user.getUsername(), user);
    }

    // select xxx from user where username = xxx
    public static User selectByUsername(String username) {
        return users.get(username);
    }

    public static List<User> listUsers(String username,
                                       String gender,
                                       Integer maxAge,
                                       Integer minAge) {
        // 多条件检索两种思路：
        // 1、StreamAPI -> filter()
        // return users.values().stream()
        //         .filter() // username
        //         .filter() // gender
        //         .filter() // maxAge
        //         .filter() // minAge
        //         .collect(Collectors.toList());

        // 2、手写遍历+判断过程
        List<User> list = new ArrayList<>();
        // 遍历Map集合的值列视图
        for (User user : users.values()) {
            // 正向思路：如果当前用户满足所有查询条件，则添加到list集合中
            // if ((username == null || user.getUsername().contains(username))
            //     && (gender == null || user.getGender().equals(gender))
            //     && (maxAge == null || user.getAge() < maxAge)
            //     && (minAge == null || user.getAge() > minAge)) {
            //     list.add(user);
            // }
            if (username != null && !user.getUsername().contains(username)) {
                continue;
            }
            if (gender != null && !user.getGender().equals(gender)) {
                continue;
            }
            if (maxAge != null && user.getAge() > maxAge) {
                continue;
            }
            if (minAge != null && user.getAge() < minAge) {
                continue;
            }
            list.add(user);

        }
        return list;
    }
}
