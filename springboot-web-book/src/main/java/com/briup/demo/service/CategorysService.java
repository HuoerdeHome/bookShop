package com.briup.demo.service;

import com.briup.demo.entity.Category;
import com.briup.demo.mapper.categoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 14409
 */
@Component//作用是将这个类标注为spring中的组件，使其能够被自动检测、实例化和自动注入
//控制反转IOC和依赖注入DI
public class CategorysService {//service层
   @Autowired
   private categoryMapper categoryMapper;

   public List<Category> listChildren() {
      return categoryMapper.list();
   }


}
