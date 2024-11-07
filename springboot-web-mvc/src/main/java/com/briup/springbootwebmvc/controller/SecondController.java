package com.briup.springbootwebmvc.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 14409
 * 添加注解controll，对组件进行注册
 * 定义方法
 * 映射方法：三个方面：
 * 1，参数部分
 * 2.返回值部分
 * 方法的返回值，就是返回给前端的响应内容
 * 1）同步 请求：带有完整的html代码
 * 通常会表示要跳转到的那个资源的路径
 * 常用的类型有两种：
 * modeandview：
 * string：字符串来表示要跳转的 `逻辑视图名称`
 * 前面可以添加两种前缀：reward：内部跳转（默认）   redirect：重定向  通信成本，外部资源
 *
 * 2）异步 请求
 * 体部就是客户端需要的数据：‘返回值可以是任意类型：无论是什么数据类型，都会被spring bootmvc转换为JSON数据格式
 * 需要添加@resquestBody注解：
 * 不添加表示逻辑视图名称
 * 如果一个方法都是响应数据就在类上体检requestBody注解即可
 *
 *
 * 3，方法上的注解
 * 1)requestmapper   value：返回接口 method :允许使用的http请求方法
 * 2）@getmapper、@postmapper、@deletemapper、、、
 *
 *路径的拼接
 * 一个映射方法的url一共由两个号部分构成
 * 类上添加的映射路径：requearmappper定义在类上相当于在所有的
 * 方法上的映射路径
 */

@RequestMapping("/second")
//@Controller
//@ResponseBody
@RestController

public class SecondController {
    //@RequestMapping(value = "/test0",method = RequestMethod.GET)
    @GetMapping("/test0")
    public void test0(){
        System.out.println("test0....");
    }
    @GetMapping("/test1")
    public String test1(){
        System.out.println("test1....");
        //默认内部转发形式
       // return "forward/index.html";//同步请求的页面跳转  请求转发（内部资源跳转）  重定向转发

        //重定向：
        return "redirect:/index.html";
    }

    @GetMapping("/test2")
    public String test2(){
        System.out.println("test2....");
        return "index";
    }
    @GetMapping("/test3")
    @ResponseBody
    public String test3(){
        System.out.println("test3....");

        return "/index.html";
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class student{
        private String name;
        private String gender;
        private Integer age;
    }
    @GetMapping("/test4")
    @ResponseBody
    public student test4(){
        System.out.println("test4....");

        return new student("tom","男",23);
    }
}
