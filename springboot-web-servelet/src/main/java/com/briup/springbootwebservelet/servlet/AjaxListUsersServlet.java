package com.briup.springbootwebservelet.servlet;

import com.alibaba.fastjson2.JSONObject;
import com.briup.demo.util.Result;
import com.briup.springbootwebservelet.mapper.UserMapper;
import com.briup.demo.entity.User;
import com.briup.springbootwebservelet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author 14409
 */
@WebServlet("/listUsers")
public class AjaxListUsersServlet extends HttpServlet {

    @Autowired
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String gender = request.getParameter("gender");

        String minAgeStr = request.getParameter("minAge");
        String maxAgeStr = request.getParameter("maxAge");
        Integer minAge = null;
        Integer maxAge = null;
        if (minAgeStr != null && !"".equals(minAgeStr)) {
            minAge = Integer.parseInt(minAgeStr);
        }
        if (maxAgeStr != null && !"".equals(maxAgeStr)) {
            maxAge = Integer.parseInt(maxAgeStr);
        }

        // 调用Service执行查询
        List<User> users = userService.list(username, minAge, maxAge, gender);

        Result result = Result.success(users);
        String jsonStr = JSONObject.toJSONString(result);

        PrintWriter writer = response.getWriter();
        writer.write(jsonStr);
        writer.flush();
    }
}