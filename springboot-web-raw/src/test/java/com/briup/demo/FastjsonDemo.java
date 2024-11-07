package com.briup.demo;

import com.alibaba.fastjson2.JSONObject;
import com.briup.demo.entity.User;

/**
 * @author YuYan
 * @date 2024-10-28 14:28:11
 */
public class FastjsonDemo {

    public static void main(String[] args) {
        String jsonStr = "{\"username\":\"admin\",\"password\":\"123123\"}";
        // 1、JSON字符串转对象
        // 1）转JSONObject对象（一个HashMap集合）
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        System.out.println(jsonObject.getString("username"));
        System.out.println(jsonObject.getString("password"));
        // 2）转某个具体类型实体类对象
        User user = JSONObject.parseObject(jsonStr, User.class);
        System.out.println(user);

        // 2、如何在Java中生成JSON结构字符串
        // 1）基于JSONObject类型，先设置好键值对，然后转换
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("username", "tom");
        jsonObj.put("password", "123");
        jsonStr = jsonObj.toJSONString();
        System.out.println(jsonStr);
        // 2）直接用一个实体类对象自动转换
        user = new User();
        user.setUsername("tom");
        user.setPassword("123");
        jsonStr = JSONObject.toJSONString(user);
        System.out.println(jsonStr);
    }

}
