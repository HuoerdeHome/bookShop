package com.briup.demo.util;

import lombok.Builder;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author YuYan
 * @date 2024-10-25 09:24:42
 */
@Builder
public class HttpResponse {

    // 协议及版本
    private final String protocol;
    // 响应状态
    private ResponseCode responseCode;
    // 响应头部字段
    private Map<String, String> responseHeaders;
    // 响应体部
    @Setter
    private String responseBody;

    // 提供一个添加头部字段的方法
    public void addHeader(String headerName,
                          String headerValue) {
        if (responseHeaders == null) {
            responseHeaders = new HashMap<>();
        }
        responseHeaders.put(headerName, headerValue);
    }

    // 设置成功状态
    public void ok() {
        this.responseCode = ResponseCode.OK;
    }

    // 提供一个专门用来设置Content-Length的方法
    public void setContentLength(int length) {
        addHeader("Content-Length",
                String.valueOf(length));
    }
    // 提供一个专门用来设置Content-Type的方法
    public void setContentType(String type) {
        addHeader("Content-Type", type);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // 协议及版本
        sb.append(protocol)
            .append(" ")
            .append(responseCode.code())
            .append(" ")
            .append(responseCode.codeMessage())
            .append("\n");
        if (responseHeaders != null) {
            for (Map.Entry<String, String> entry : responseHeaders.entrySet()) {
                sb.append(entry.getKey())
                        .append(": ")
                        .append(entry.getValue())
                        .append("\n");
            }
        }
        // 空行
        sb.append("\n");
        // 体部
        sb.append(responseBody);
        return sb.toString();
    }


    public void notFound() {
        String body = "<html>" +
                "<body>" +
                "<h1>404 - 请求的资源未找到！</h1>" +
                "<hr>" +
                "<h2>NOT FOUND</h2>" +
                "</body>" +
                "</html>";
        this.responseCode = ResponseCode.NOT_FOUND;
        this.responseBody = body;
        setContentLength(body.getBytes().length);
        setContentType("text/html;charset=utf-8");
    }
}
