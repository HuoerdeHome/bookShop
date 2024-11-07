package com.briup.springbootwebservelet.servlet;

import com.alibaba.fastjson2.JSONObject;
import com.briup.demo.util.Result;
import com.briup.springbootwebservelet.exception.CustomeException;
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
@WebServlet("/ajax-login")
public class AjaxloginServlet extends HttpServlet {
    @Autowired
    private UserService userService;

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        // 读取请求体部中的JSON字符串
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        char[] cbuf = new char[128];
        int len;
        while ((len = reader.read(cbuf)) != -1) {
            sb.append(cbuf, 0, len);
        }
        String jsonStr = sb.toString();

        // 把JSON字符串转化为Java对象
        User user = JSONObject.parseObject(jsonStr, User.class);

        // 处理业务
        Result result;
        try {
            userService.register(user);
            result = Result.success("注册成功！");
        } catch (CustomeException e) {
            result = Result.failure(e.getMessage());
        }

        // 返回响应
        jsonStr = JSONObject.toJSONString(result);
        PrintWriter writer = response.getWriter();
        writer.write(jsonStr);
        writer.flush();
    }


//    @Autowired
//    private UserService userService;
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        doPost(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
////        req.setCharacterEncoding("UTF-8");
////        resp.setCharacterEncoding("UTF-8");
////        resp.setContentType("test/plain;charset=utf-8");
//        //System.out.println("login....");
//        // 1、读取请求参数
//        // 1）表单格式参数 key=value&key=value&key=value
//        // 无论是Get请求还是Post请求，参数无论拼在URL后边还是放在报文体部的，
//        // 都是通过request.getParameter()方法读取参数
////         String username = req.getParameter("username");
////         String password = req.getParameter("password");
////         System.out.println("username = " + username);
////         System.out.println("password = " + password);
//
//        // 2）JSON结构数据（只能放在体部传递）
//        // 原生的Servlet并不支持自动解析JSON格式数据
//        // Servlet流行的年代，主流的开发技术还是前后端不分离，
//        // 参数主要以表单的形式提交。
//
//        // 手动读取到请求报文体部信息
//        // I.获取到请求对象中的一个输入流
//        // 使用这个输入流读取数据，读到的就是报文体部的数据
//        BufferedReader reader = req.getReader();
//        StringBuilder sb = new StringBuilder();
//        int len;
//        char[] cbuf = new char[128];
//        while ((len = reader.read(cbuf)) != -1) {
//            sb.append(cbuf, 0, len);
//        }
//        String jsonStr = sb.toString();
////        // 将JSON字符串转化为Java对象
//        User user = JSONObject.parseObject(jsonStr, User.class);
//        // 从参数对象user中取出username和password
////        String username = user.getUsername();
////        String password = user.getPassword();
////        System.out.println("username = " + username);
////        System.out.println("password = " + password);
////
////        // 2、处理业务逻辑
////        // 判断账号是否存在、判断密码是否正确
////        // 假设每次登录提交的账号密码都是正确的
//        //User recode = userMapper.selectByUsername(username);
//        // 调用Service层处理业务
//        Result result;
//        try {
//            userService.login(user.getUsername(),
//                    user.getPassword());
//            // 返回成功响应
//            result = Result.success("登录成功！");
//        } catch ( CustomeException e) {
//            result = Result.failure(e.getMessage());
//        }
//        // 给前端返回响应
//        // 获取到响应对象，将它转换为JSON字符串
//        jsonStr = JSONObject.toJSONString(result);
//        // 获取响应输出流，通过这个流对象输出的数据，就会被写入到响应报文的体部
//        PrintWriter writer = resp.getWriter();
//        writer.write(jsonStr);
//        writer.flush();
//
////        if (recode==null){
////            Result.failure("账号不存在");
////        }else if (!recode.getPassword().equals(password)){
////            Result.failure("密码错误");
////        }else {
////            Result.success("登录成功");
////        }
//
////        // 3、返回响应
////        // 1）同步请求
////        // I.服务器内部转发，跳转到ajax-index.html主页面
////        // req.getRequestDispatcher("/html/ajax-index.html")
////        //             .forward(req, resp);
////
////        // II.重定向跳转
////        // resp.sendRedirect("/html/ajax-index.html");
////        // resp.sendRedirect("https://www.baidu.com/");
////
////        // 2）异步请求
////        // 获取到响应对象，将它转换为JSON字符串
////        Result result = Result.success();
////        jsonStr = JSONObject.toJSONString(result);
////        // 获取响应输出流，通过这个流对象输出的数据，就会被写入到响应报文的体部
////        PrintWriter writer = resp.getWriter();
////        writer.write(jsonStr);
////        writer.flush();
//    }
}
