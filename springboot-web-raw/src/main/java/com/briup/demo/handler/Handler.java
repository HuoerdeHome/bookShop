package com.briup.demo.handler;

import com.briup.demo.util.HttpRequest;
import com.briup.demo.util.HttpResponse;

/**
 * 处理器接口
 *
 * 定义所有的动态资源处理器方法规范
 * @author YuYan
 * @date 2024-10-25 14:45:46
 */
public interface Handler {

    // 业务方法
    void service(HttpRequest request,
                 HttpResponse response);

}
