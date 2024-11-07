package com.briup.demo.handler;

import com.briup.demo.annotation.WebHandler;
import com.briup.demo.util.HttpRequest;
import com.briup.demo.util.HttpResponse;

/**
 * @author YuYan
 * @date 2024-10-25 17:25:16
 */
@WebHandler("/ajax")//、/ajax这个路径表明当前端http请求的包含/ajax的请求时，由当前注解路径的方法来处理
public class AjaxHandler implements Handler {
    @Override
    public void service(HttpRequest request,
                        HttpResponse response) {
        String body = "Hi, Browser!";
        int length = body.length();
        response.setContentLength(length);
        response.setResponseBody(body);
    }
}
