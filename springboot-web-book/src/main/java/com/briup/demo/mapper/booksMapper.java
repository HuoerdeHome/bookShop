package com.briup.demo.mapper;

import com.briup.demo.entity.Book;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author 14409
 */
//书籍映射接口：这里是实现相关业务的方法

public interface booksMapper {//dac层
    //这里的业务逻辑实现是通过关键字来查询数据
    List<Book> list(@Param("keyword") String keyword,
                    @Param("categoryId") Integer categoryId);

    Book selectById(@Param("id")Integer id);

    int insert(Book book);

    //这里是dao层，添加数据
    //List<Book>addBook(Book book);
    //修改书籍信息
    int  update(Book book);

    //批量删除
    int delete(List<Integer> ids);

    //分页查询数据方法
    List<Book> pagesQuery(@Param("pagesNum") int pagesNum,
                          @Param("pagesSize") int pagesSize,
                          @Param("publisher")String publisher);

    //分页查询总数据量的方法

    int  pagesTotal(@Param("publisher")String publisher);

}
