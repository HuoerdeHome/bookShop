package com.briup.demo.service;

import com.briup.demo.Util.Pages;
import com.briup.demo.entity.Book;
import com.briup.demo.exception.BookException;
import com.briup.demo.mapper.booksMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 14409
 */
@Component//注解维一个bean
public class BooksService {//service层
    @Autowired//自导注入并且切实例化  IOC控制反转
    private booksMapper bookMapper;

    public List<Book> list(String keyword,
                           Integer categoryId) {
        return bookMapper.list(keyword, categoryId);
    }

    //
    public Book getByid(Integer id) {
        return bookMapper.selectById(id);
    }

    //自己写的
    public void addBooks(Book book) throws BookException {
        //新增数据是默认状态是1
        //避免魔法值的出现
        final int statusE = 0;
        final int statusNE = 1;
        book.setStatus(statusE);
        //调用dac层
        int result = bookMapper.insert(book);
        if (result != statusNE) {
            throw new BookException("新增失败，稍后重试");

        }

    }

    public void update(Book book) throws BookException {
        int result = bookMapper.update(book);
        if (result != 1) {
            throw new BookException("bbbbb");
        }
    }

    public void delete(List<Integer> ids) {
        int size;
        //参数校验：
        if (ids == null || (size = ids.size()) == 0) {
            return;
        }
        int result = bookMapper.delete(ids);
        if (result < size) {
            throw new BookException("有"+result+"数据删除，"+"有"+(size-result)+"条删除失败");
        }
    }
    public Pages<Book> pagesQuery(int pageNum ,int pagesSize,String publisher){

        List<Book> books = bookMapper.pagesQuery(pageNum, pagesSize,publisher);
        Pages<Book> pages=new Pages<Book>();
        int i = bookMapper.pagesTotal(publisher);
        pages.setData(books);
        pages.setTotal(i);

        return  pages;
    }









    // private BookMapper bookMapper;    // 物理删除
    // private Book1Mapper bookMapper;   // 逻辑删除方案一
    // private Book2Mapper bookMapper;      // 逻辑删除方案二
//    private Book3Mapper bookMapper;      // 逻辑删除方案三
//
//    public List<Book> list(String keyword,
//                           Integer categoryId) {
//        return bookMapper.list(keyword, categoryId);
//    }
//
//    public Book get(Integer id) {
//        return bookMapper.selectById(id);
//    }
//
//    public void add(Book book) throws BookException {
//        // 新增图书时状态默认为上架
//        book.setStatus(GlobalConstant.bookStatusEnabled);
//
//        // 调用dao层将数据插入表中
//        int result = bookMapper.insert(book);
//        if (result != 1) {
//            throw new BookException("新增失败，请稍后重试！");
//        }
//    }
//
//    public void update(Book book) throws BookException {
//        int result = bookMapper.update(book);
//        if (result != 1) {
//            throw new BookException("修改失败，请稍后重试！");
//        }
//    }
//
//    public void delete(List<Integer> ids) throws BookE xception {
//        // 一、物理删除
//        // 参数校验，集合必须不为空且长度大于零
//        int size;
//        if (ids == null || (size = ids.size()) == 0) {
//            return;
//        }
//        int result = bookMapper.delete(ids);
//        if (result < size) {
//            throw new BookException("有" + result + "条数据删除成功，" +
//                    "有" + (size - result) + "条删除失败！");
//        }

        // 二、逻辑删除方案二
        // 1、先查出目标数据并且插入到删除历史记录表中
        // bookMapper.moveToDelete(ids);
        // 2、从原表中物理删除掉目标数据
        // bookMapper.delete(ids);

}
