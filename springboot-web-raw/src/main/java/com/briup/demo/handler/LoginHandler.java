package com.briup.demo.handler;

import com.briup.demo.annotation.WebHandler;
import com.briup.demo.util.HttpRequest;
import com.briup.demo.util.HttpResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录功能处理器
 *
 * @author YuYan
 * @date 2024-10-25 14:27:08
 */
@WebHandler("/login")
public class LoginHandler implements Handler {

    /**
     * 处理登录请求的方法
     */
    @Override
    public void service(HttpRequest request,
                      HttpResponse response) {
        // 登录请求
        // 接口文档：username password
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // 调用数据库
        // select * from t_user
        // where username = #{username}
        Map<String, String> userinfo = new HashMap<>();
        userinfo.put("tom", "tom123");
        userinfo.put("jack", "jack123");
        userinfo.put("lucy", "lucy123");
        userinfo.put("lily", "lily123");
        // 模拟处理业务的流程
        // 1）判断账号是否存在
        String realPassword = userinfo.get(username);

        String result;
        if (realPassword == null) {
            // 账号不存在，给前端返回错误信息
            result = "账号不存在！";
        } else {
            // 2）继续判断密码是否正确
            if (password.equals(realPassword)) {
                // 密码正确，登录成功
                result = "登录成功！";
            } else {
                // 密码错误，登录失败
                result = "密码错误！";
            }
        }
        String body = "<html>" +
                "<body>" +
                "<h1>" + result + "</h1>" +
                "</body>" +
                "</html>";
        response.setContentType("text/html;charset=utf-8");
        response.setContentLength(body.getBytes().length);
        response.setResponseBody(body);
    }

}
