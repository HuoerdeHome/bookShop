package com.briup.demo.exception;

/**
 * @author 14409
 */
//自定义异常类
public class BookException extends RuntimeException{
    public BookException(){

    }
    public BookException(String message){
        super(message);
    }
}
