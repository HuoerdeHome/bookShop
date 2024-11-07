package com.briup.demo.handler;

import com.alibaba.fastjson2.JSONObject;
import com.briup.demo.annotation.WebHandler;
import com.briup.demo.db.UserTable;
import com.briup.demo.entity.User;
import com.briup.demo.util.HttpRequest;
import com.briup.demo.util.HttpResponse;
import com.briup.demo.util.Result;

/**
 * @author YuYan
 * @date 2024-10-28 17:07:24
 */
@WebHandler("/ajax-register")
public class AjaxRegisterHandler implements Handler {

    @Override
    public void service(HttpRequest request, HttpResponse response) {
        System.out.println("AjaxRegisterHandler....");
        // 从请求对象中取出体部数据（JSON字符串）
        String jsonStr = request.getBody();
        // 将JSON字符串转换为User对象
        User user = JSONObject.parseObject(jsonStr, User.class);
        System.out.println(user);
        // 判断该用户名是否已被占用
        User record = UserTable.selectByUsername(user.getUsername());
        Result result;
        if (record == null) {
            // 用户名不存在，允许注册
            UserTable.add(user);
            // 给前端返回成功响应
            result = Result.success("注册成功！");
        } else {
            // 给前端返回错误响应
            result = Result.failure("该用户名已被他人占用！");
        }
        response.setContentType("text/plain;charset=utf-8");
        jsonStr = JSONObject.toJSONString(result);
        response.setContentLength(jsonStr.getBytes().length);
        response.setResponseBody(jsonStr);
    }
}
