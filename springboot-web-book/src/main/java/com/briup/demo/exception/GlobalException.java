package com.briup.demo.exception;

import com.briup.demo.entity.Book;
import com.briup.demo.response.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 14409
 * 全局异常处理器
 * 两大作用：
 * 统一对collect出现的已异常进行捕获，降低代码的冗余
 * 程序中可能抛出的已成返回到前端的最后一道保障；
 *保障正常提供服务的最后保障
 * 控制程序不会直接将逻辑错误返回给前端
 *
 *
 * 1.再类上添加一个注解@RestControllerAdvice
 * 之后这个方法 的返回值和collect里面二等方法返回值类型一致。
 * 2.定义一个统一异常处理方法注解@ExceptionHandler
 * 类的镜像双亲委托机制：指定要去拦截处理的异常类型：设置为最高类型
 * 如果需要对异常对象进行处理。可以定义赶一个exception 属性
 *
 */
@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(value = Exception.class)//可能出现的所有的异常
    public Result handle(Exception  e){
        //需要对类型进行判断，
        //1.我们自己上抛的异常
        //2.未知的异常
        if (e instanceof BookException){
            return Result.failure(e.getMessage());
        }
        return Result.failure("服务器繁忙，稍后重试");
    }
}
