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
 * @date 2024-10-28 11:41:46
 */
@WebHandler("/ajax-login")
public class AjaxLoginHandler implements Handler {

    @Override
    public void service(HttpRequest request, HttpResponse response) {
        // 通过Request对象，将请求参数读取出来
        // String username = request.getParameter("username");
        // String password = request.getParameter("password");
        // System.out.println("username = " + username);
        // System.out.println("password = " + password);

        // 获取到Request对象中保存的体部数据（JSON字符串）
        String body = request.getBody();
        // 把请求报文体部的JSON字符串自动解析封装为一个Java实体类对象
        User user = JSONObject.parseObject(body, User.class);

        String username = user.getUsername();
        String password = user.getPassword();

        Result result = null;
        User record = UserTable.selectByUsername(username);
        if (record == null) {
            result = Result.failure("该用户名未注册！");
        } else if (!record.getPassword().equals(password)) {
            result = Result.failure("密码错误！");
        } else {
            result = Result.success("登录成功！");
        }
        response.setContentType("text/plain;charset=utf-8");
        // 把封装好的Result响应对象转换为JSON字符串
        String jsonStr = JSONObject.toJSONString(result);
        System.out.println(jsonStr);
        response.setContentLength(jsonStr.getBytes().length);
        response.setResponseBody(jsonStr);
    }

}
