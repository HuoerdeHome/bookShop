package com.briup.springbootwebservelet.servlet;

import com.alibaba.fastjson2.JSONObject;
import com.briup.demo.util.Result;
import com.briup.springbootwebservelet.exception.CustomeException;
import com.briup.springbootwebservelet.mapper.UserMapper;
import com.briup.demo.entity.User;
import com.briup.springbootwebservelet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 14409
 */
@WebServlet("/ajax-register")
public class AjaxRegisterServlet  extends HttpServlet {

    @Autowired
    private UserService  userService;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("UTF-8");
//        resp.setCharacterEncoding("UTF-8");
//        resp.setContentType("text/pain;charset=utf-8");
        //获取到相关的输入流
        BufferedReader reader = req.getReader();

        StringBuilder sb = new StringBuilder();
        int len;
        char[] cbuf = new char[128];
        while ((len = reader.read(cbuf)) != -1) {
            sb.append(cbuf, 0, len);
        }
        String jsonStr = sb.toString();
//        // 将JSON字符串转化为Java对象
        User user = JSONObject.parseObject(jsonStr, User.class);
        //User recode = userMapper.selectByUsername(user.getUsername());
        Result result;
        try {
            userService.register(user);
            // 返回成功响应
            result = Result.success("注册成功！");
        } catch ( CustomeException e) {
            result = Result.failure(e.getMessage());
        }
        jsonStr=JSONObject.toJSONString(result);
        PrintWriter writer = resp.getWriter();
        writer.write(jsonStr);
        writer.flush();

//        if (recode!=null){
//            result = Result.failure("用户被注册");
//        }else{
//            int update = userMapper.insert(user);
//            if (update==1){
//              result= Result.success();
//            }else{
//                result=Result.failure("服务器繁忙");
//            }
//        }


//        String jsonString = JSONObject.toJSONString(result);
//        PrintWriter writer = resp.getWriter();
//        writer.write(jsonString);
//        writer.flush();

    }
}
