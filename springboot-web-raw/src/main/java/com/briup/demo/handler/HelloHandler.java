package com.briup.demo.handler;

import com.briup.demo.annotation.WebHandler;
import com.briup.demo.util.HttpRequest;
import com.briup.demo.util.HttpResponse;

/**
 * @author YuYan
 * @date 2024-10-29 15:31:27
 */
@WebHandler("/hello")
public class HelloHandler implements Handler{
    @Override
    public void service(HttpRequest request, HttpResponse response) {
        String body = "<h1>Hello!</h1>";
        response.setResponseBody(body);
        response.setContentType("text/html;charset=utf-8");
        response.setContentLength(body.getBytes().length);
    }
}
