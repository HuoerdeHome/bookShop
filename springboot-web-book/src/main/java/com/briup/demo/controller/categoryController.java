package com.briup.demo.controller;

import com.briup.demo.entity.Category;
import com.briup.demo.response.Result;
import com.briup.demo.service.CategorysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 14409
 */
@RestController//
@RequestMapping("/category")//requestMapper整个注解的作用是来映射http请求到特定的处理方法上
public class categoryController {//分类控制器
    @Autowired
    private CategorysService categoryService;
    @GetMapping
    public Result list(){
       List<Category>categories= categoryService.listChildren();//web层
       return Result.success(categories);
    }
}
