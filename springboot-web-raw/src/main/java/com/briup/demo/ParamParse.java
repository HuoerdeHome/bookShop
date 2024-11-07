package com.briup.demo;

import java.net.URLDecoder;

/**
 * @author YuYan
 * @date 2024-10-21 15:16:37
 */
public class ParamParse {
    public static void main(String[] args) throws Exception {
        String paramStr = "username=briup&password=123&gender=male&hobby=music&hobby=game&age=22&dob=2024-10-01&email=123%40qq.com&introduction=asdsadasd&education=1";
        // URL解码
        paramStr = URLDecoder.decode(paramStr, "UTF-8");
        System.out.println(paramStr);
        // 根据&符号进行字符串分割，得到单个参数
        String[] params = paramStr.split("&");
        for (String param : params) {
            // System.out.println(param);
            // 每一组参数通过"="符号进行分割，得到单独的参数名和参数值
            String[] aParam = param.split("=");
            String paramName = aParam[0];
            String paramValue = aParam[1];
            System.out.println(paramName + ": " + paramValue);
        }
    }
}
