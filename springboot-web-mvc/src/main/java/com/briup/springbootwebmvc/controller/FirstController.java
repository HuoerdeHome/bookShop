package com.briup.springbootwebmvc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 14409
 * 第一种：定义controller的方式（传统和古老的方法）
 * 1.让Java类实现一个controller接口
 * 2.重写handlerequest方法，类似servlet中的service方法，
 * 用来请求处理，当请求到来的时候，框架会自动调用
 * 3.modleAndView：前后段不分离：同时对数据和页面进行处理
 *
 *
 *
 * 问题：
 * 重写方法比较复杂：
 *一个类只能定义一个接口实现一个方法
 * 参数的的定义复杂
 * 返回值固定
 * 注册和配置复杂
 *
 *
 * 自定义controller类：通过一个注解来实现相关的方法
 */
@org.springframework.stereotype.Controller
@RequestMapping("/")
public class FirstController implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/index.html");
        modelAndView.addObject("username", "password");
        return modelAndView;
    }
}
