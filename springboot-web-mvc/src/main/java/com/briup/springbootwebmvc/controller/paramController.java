package com.briup.springbootwebmvc.controller;

import com.briup.springbootwebmvc.entity.Student;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author 14409
 * 总体的原则L：
 * 所有关于请求报文的相关内容，都可以通过参数传递来调用
 * 参数传递绑定
 * 根据自己的需求来定义的类型，\
 * 直接定义表单参数来获取:一定是表单参数格式
 *
 * 集合和实体类对象；
 *
 */
@RestController
@RequestMapping("/param")
public class paramController {
    @GetMapping("/test0")
    public String test0(HttpServletRequest request,
                        HttpServletResponse response){
        System.out.println("response = " + response);
        System.out.println("request = " + request);
        System.out.println("request.getParameter(\"username\") = " + request.getParameter("username"));
        System.out.println("request.getParameter(\"password\") = " + request.getParameter("password"));
        return  "ok";
    }
    @GetMapping("/test1")
    public String test1(String username,
                        String password){
        System.out.println("username = " + username);
        System.out.println("password = " + password);
        return  "ok";
    }
    @GetMapping("/test3")
    public String test3(Student stu){
        System.out.println(stu);
        return "ok";
    }
    @GetMapping("/test4")
    public String test4(@RequestParam(name = "stuName") String name,
                        @RequestParam(name = "gender",required = false)String gender,
                        @RequestParam(name = "age",required = false)Integer age,
                        @RequestParam(name = "pageNum",required = false,defaultValue = "1") int pageNum,
                        @RequestParam(name = "pageSize",required = false,defaultValue = "2") int pageSize){
        System.out.println(name);
        System.out.println(gender);
        System.out.println(age);
        System.out.println("pageNum = " + pageNum);
        System.out.println("pageSize = " + pageSize);
        return "ok";
    }
    @GetMapping("/test5")
    public String test5(@RequestParam Map<?,?> map){
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            System.out.println("entry.getKey()+entry.getValue() = " + entry.getKey() + entry.getValue());
        }
        return "ok";
    }
    @PostMapping("/test6")
    public String test6(@RequestBody String body){
        System.out.println(body);

        return "ok";
    }
    //路径参：把参数当成路径中的参
    //查询参数u
    @GetMapping("/test11/{param}")
    public String test11(@PathVariable("param") Integer param) {
        System.out.println(param);
        return "ok";

    }
    //参数：要求携带某种特使的参数传递请求参数
    @GetMapping(value = "/test12",params ="Username" )//params参数表示路径必须包含Username
    public String test12(){

        return "ok";
    }





}
