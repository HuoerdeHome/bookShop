package com.briup.demo.mapper;

import com.briup.demo.entity.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 14409
 */
//书籍映射接口：这里是实现相关业务的方法

public interface booksMapper1 {//dac层
    //这里的业务逻辑实现是通过关键字来查询数据
    List<Book> list(@Param("keyword") String keyword,
                    @Param("categoryId") Integer categoryId);

    Book selectById(@Param("id") Integer id);

    int insert(Book book);

    int update(Book book);

    int delete(List<Integer> ids);
}
