package com.briup.demo.controller;

import com.briup.demo.Util.Pages;
import com.briup.demo.entity.Book;
import com.briup.demo.exception.BookException;
import com.briup.demo.response.Result;
import com.briup.demo.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 14409
 */
//组合注解：是@Controller和@ResponseBody的组合注解，表示该类是一个控制器，并且所有方法默认都会以JSON格式返回数据。
@RestController
//用于映射HTTP请求到特定的处理方法上。可以用于类级别和方法级别。
@RequestMapping("/book")
//书籍控制器
public class bookController {//web层


    @Autowired//自动注入给springIOC容器
    private BooksService bookService;


    //根据关键字和分类查询功能
    @GetMapping
    public Result list(String keyword,
                       Integer categoryId) {
        List<Book> books = bookService.list(keyword, categoryId);
        return Result.success(books);
    }

    //根据id来查询书籍 添加，删除书籍功能
    //http://localhost:8088/book/1  @PathVariable路径参来源
    @GetMapping("/{id}")
    public Result getByid(@PathVariable("id") Integer id){
        Book byid = bookService.getByid(id);
        return Result.success(byid);
    }

    //添加书籍
    //自己写的//@RequestBody体部数据
    @PostMapping
    public Result add(@RequestBody Book book){

            bookService.addBooks(book);
            return Result.success();

    }

    //上下架和修改功能
    @PutMapping
    public Result update(@RequestBody Book book)throws BookException{

            bookService.update(book);
            return Result.success();


    }
    //删除功能：单条和多条
    @DeleteMapping("/{ids}")
        public Result delete(@PathVariable("ids") List<Integer> ids)throws BookException{
              bookService.delete(ids);
            return Result.success();

    }

    @GetMapping(params = {"pagesNum","pagesSize"})
    public Result pagesQuery(@RequestParam("pagesNum")int pagesNum,
                             @RequestParam("pagesNum")int pagesSize,
                             @RequestParam("publisher")String publisher){//前端传参
        Pages<Book> pages = bookService.pagesQuery(pagesNum, pagesSize,publisher);
        return Result.success(pages);
    }

}
