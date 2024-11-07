package com.briup.demo.handler;

import com.briup.demo.annotation.WebHandler;
import com.briup.demo.util.HttpRequest;
import com.briup.demo.util.HttpResponse;

/**
 * @author YuYan
 * @date 2024-10-29 14:38:29
 */
@WebHandler("/updateInfo")
public class UpdateInfoHandler implements Handler {
    @Override
    public void service(HttpRequest request, HttpResponse response) {

    }
}
