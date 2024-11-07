package com.briup.demo.handler;

import com.alibaba.fastjson2.JSONObject;
import com.briup.demo.annotation.WebHandler;
import com.briup.demo.db.UserTable;
import com.briup.demo.entity.User;
import com.briup.demo.util.HttpRequest;
import com.briup.demo.util.HttpResponse;
import com.briup.demo.util.Result;

import java.util.List;

/**
 * @author YuYan
 * @date 2024-10-29 10:16:34
 */
@WebHandler("/listUsers")
public class ListUsersHandler implements Handler {

    @Override
    public void service(HttpRequest request, HttpResponse response) {
        // 从request对象中把URL中的请求参数获取到
        String username = request.getParameter("username");
        String gender = request.getParameter("gender");
        String maxAgeStr = request.getParameter("maxAge");
        String minAgeStr = request.getParameter("minAge");
        Integer maxAge = null;
        Integer minAge = null;
        if (maxAgeStr != null) {
            maxAge = Integer.parseInt(maxAgeStr);
        }
        if (minAgeStr != null) {
            minAge = Integer.parseInt(minAgeStr);
        }

        // 执行查询
        List<User> list = UserTable.listUsers(username, gender,
                maxAge, minAge);

        // 把查询结果数据封装为Result响应模型
        Result result = Result.success(list);

        // 转换成JSON数据（JSON数组）
        String jsonStr = JSONObject.toJSONString(result);
        System.out.println(jsonStr);

        response.setContentLength(jsonStr.getBytes().length);
        response.setContentType("text/plain;charset=utf-8");
        response.setResponseBody(jsonStr);
    }

}
