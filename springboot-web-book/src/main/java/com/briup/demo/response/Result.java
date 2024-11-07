package com.briup.demo.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

/**
 * 所有给前端返回的响应数据的统一模型
 *
 * @author YuYan
 * @date 2024-10-28 15:07:17
 */
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class Result {

    private final static Integer SUCCESS = 1; // 成功
    private final static Integer FAILURE = 0; // 失败

    private final static String DEFAULT_SUCCESS_MESSAGE = "操作成功！";
    private final static String DEFAULT_FAILURE_MESSAGE = "操作失败！";
    // 状态码，0-失败，1-成功
    private Integer code;
    // 响应消息
    private String message;
    // 响应数据
    private Object data;

    // 请求成功
    public static Result success() {
        return success(DEFAULT_SUCCESS_MESSAGE);
    }
    // 请求成功
    public static Result success(String message) {
        return success(message, null);
    }
    // 请求成功
    public static Result success(Object data) {
        return success(DEFAULT_SUCCESS_MESSAGE, data);
    }
    // 请求成功
    public static Result success(String message, Object data) {
        return Result.builder()
                .code(SUCCESS)
                .message(message)
                .data(data)
                .build();
    }

    public static Result failure() {
        return failure(DEFAULT_FAILURE_MESSAGE);
    }
    public static Result failure(String message) {
        return Result.builder()
                .code(FAILURE)
                .message(message)
                .build();
    }


}
