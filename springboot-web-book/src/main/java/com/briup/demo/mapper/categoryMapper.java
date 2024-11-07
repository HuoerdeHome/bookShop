package com.briup.demo.mapper;

import com.briup.demo.entity.Book;
import com.briup.demo.entity.Category;

import java.util.List;

/**
 * @author 14409
 */
//分类映射接口
public interface categoryMapper {//dao、层
    //业务实现：按照图书的分类来查询数据
    List<Category>list();


    //
    Category  selectById(Integer id);


}
