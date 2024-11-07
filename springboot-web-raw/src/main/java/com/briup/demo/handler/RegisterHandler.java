package com.briup.demo.handler;

import com.briup.demo.annotation.WebHandler;
import com.briup.demo.util.HttpRequest;
import com.briup.demo.util.HttpResponse;

/**
 * @author YuYan
 * @date 2024-10-25 15:23:02
 */
@WebHandler("/register")
public class RegisterHandler implements Handler {
    @Override
    public void service(HttpRequest request,
                        HttpResponse response) {
        response.setResponseBody("<h1>Register Success!</h1>");
    }
}
