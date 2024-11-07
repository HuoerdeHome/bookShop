package com.briup.demo.util;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 请求类
 *
 * 该类的一个对象，就包含一次请求的全部信息。
 * 该类的一个实例对应前端提交的一封请求报文。
 * @author YuYan
 * @date 2024-10-24 15:00:37
 */
@Builder(access = AccessLevel.PACKAGE)
public class HttpRequest {

    // HTTP请求方法
    private final String requestMethod;
    // 请求URL（请求的资源路径）
    private final String requestUrl;
    // 协议及版本
    private final String protocol;
    // 请求头部字段
    private final Map<String, String> requestHeaders;
    // 请求参数信息
    private final Map<String, List<String>> requestParams;

    @Getter
    private final String body;

    // 提供一个根据字段名获取参数值的方法
    public String getParameter(String paramName) {
        List<String> values = requestParams.get(paramName);
        if (values == null) {
            return null;
        }
        return values.get(0);
    }

    // 提供一个根据字段名获取多个参数值的方法
    public List<String> getParameters(String paramName) {
        List<String> values = requestParams.get(paramName);
        if (values == null) {
            return null;
        }
        List<String> result = new ArrayList<>();
        Collections.copy(values, result);
        return result;
    }

    // 提供一个根据字段名获取头部字段值的方法
    public String getHeader(String headerName) {
        return requestHeaders.get(headerName);
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public String getProtocol() {
        return protocol;
    }
}
