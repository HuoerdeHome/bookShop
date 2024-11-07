package com.briup.demo.util;

/**
 * @author YuYan
 * @date 2024-10-25 09:29:08
 */
public enum ResponseCode {

    // 常用的响应状态码
    OK("200", "OK"),
    BAD_REQUEST("400", "BadRequest"),
    NOT_FOUND("404", "NotFound"),
    METHOD_NOT_ALLOWED("405", "MethodNotAllowed"),
    INTERNAL_SERVER_ERROR("500", "InternalServerError");

    ResponseCode(String code, String codeMessage) {
        this.code = code;
        this.codeMessage = codeMessage;
    }

    private final String code;
    private final String codeMessage;

    public String code() {
        return this.code;
    }
    public String codeMessage() {
        return this.codeMessage;
    }

}
