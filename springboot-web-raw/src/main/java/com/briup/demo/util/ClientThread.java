package com.briup.demo.util;

import com.briup.demo.handler.Handler;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * 客户端线程类
 *
 * @author YuYan
 * @date 2024-10-24 10:14:10
 */
public class ClientThread extends Thread {

    private final Socket socket;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            run0();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // 解析请求报文
    private HttpRequest parseRequestMessage(Socket socket) throws Exception {
        // 通过套接字对象获取输入流
        InputStream is = socket.getInputStream();
        // 读取客户端发送的请求报文信息
        BufferedReader br = new BufferedReader(
                new InputStreamReader(is));
        // 定义一个变量用来接收每次读取一行的结果
        String line;
        /* 开始解析报文 */
        // 1、固定调用一次readLine()方法，读取到请求行
        line = br.readLine();
        // 根据空格字符分割请求行
        String[] split = line.split(" ");
        // 请求方法，Get、Post...
        String method = split[0];
        // 请求URL，表示要访问什么资源
        String url = split[1];
        // 协议及版本 HTTP/1.1
        String protocol = split[2];

        // 判断URL中是否携带了请求参数
        split = url.split("\\?");
        /*
         * localhost:8088/?username=tom&password=123
         * split => [0]:localhost:8088/
         *          [1]:username=tom&password=123
         */
        Map<String, List<String>> params = new HashMap<>();
        if (split.length >= 2) {
            // 如果携带了URL参数，分割结果中的[0]元素才是真正的URL
            url = split[0];
            // 分割结果中[1]元素是完整的参数字符串
            parseParamStr(split[1], params);
        }
        // 创建一个Map集合用来保存所有头部字段键值对
        Map<String, String> headers = new HashMap<>();
        // 循环勾结重复执行读取操作，解析报文头部
        while (!"".equals(line = br.readLine())) {
            // 一行内容进行解析
            split = line.split(": ", 2);
            String headerName = split[0];
            String headerValue = split[1];
            headers.put(headerName, headerValue);
        }

        String body = null;

        // 如果是Post请求，则解析报文体部
        if ("Post".equalsIgnoreCase(method)) {
            // 从头部字段中查出Content-Length字段值
            int bodyLength = Integer.parseInt(headers.get("Content-Length"));
            // 创建一个和体部长度等长的缓冲数组
            char[] cbuf = new char[bodyLength];
            // 读取一次，将体部读到缓冲数组中
            br.read(cbuf);
            // 将缓冲区中的所有字符拼接为一个完整的参数字符串
            body = new String(cbuf);

            // 根据请求报文中描述的内容类型，决定如何解析体部数据
            // 1）如果是表单数据，则按照键值对参数字符串的格式解析
            // 表单数据：Content-Type: application/x-www-form-urlencoded
            String contentType = headers.get("Content-Type");
            if ("application/x-www-form-urlencoded".equals(contentType)) {
                parseParamStr(body, params);
            } else if ("application/json".equals(contentType)) {
                // 如果是非键值对结构的数据，只把原值赋值给body属性存储
            }
        }

        // 把解析好的报文信息封装为一个HttpRequest请求对象
        return HttpRequest.builder()
                .requestMethod(method)
                .requestUrl(url)
                .protocol(protocol)
                .body(body)
                .requestHeaders(headers)
                .requestParams(params)
                .build();
    }

    /**
     * 用于处理动态资源请求（访问接口）
     *
     * @param request
     * @param response
     * @return 是否处理成功（是否存在对应的处理器）
     * @throws Exception
     */
    private boolean dynamicResource(HttpRequest request,
                                    HttpResponse response) throws Exception {
        // 从请求对象中取出URL，转换为小写形式
        String requestUrl = request.getRequestUrl().toLowerCase();
        // 判断是否为该路径配置过处理器
        Handler handler = HandlerHolder.getHandler(requestUrl);
        if (handler == null) {
            return false;
        }
        handler.service(request, response);
        return true;
    }

    /**
     * 处理静态请求
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    private boolean staticResource(HttpRequest request,
                                   HttpResponse response) throws Exception {
        final String dir = "static";
        String requestUrl = request.getRequestUrl();
        InputStream is = ClassLoader.getSystemResourceAsStream(dir + requestUrl);
        if (is == null) {
            // 本地资源不存在
            return false;
        }
        InputStreamReader isr = new InputStreamReader(is);
        StringBuilder sb = new StringBuilder();
        char[] cbuf = new char[1024];
        int len;
        while ((len = isr.read(cbuf)) != -1) {
            sb.append(cbuf, 0, len);
        }
        // 设置头部字段
        String type = null;
        if (requestUrl.endsWith(".js")) {
            type = "javascript";
        } else if (requestUrl.endsWith(".css")) {
            type = "css";
        } else if (requestUrl.endsWith(".html")) {
            type = "html";
        }
        if (type != null) {
            response.setContentType("text/" + type + ";charset=utf-8");
        }
        String body = sb.toString();
        response.setContentLength(body.getBytes().length);
        response.setResponseBody(body);
        return true;
    }

    /**
     * 处理一次请求+返回响应的主体流程
     *
     * @throws Exception
     */
    private void run0() throws Exception {
        // 1、解析请求报文信息，封装为HttpRequest对象
        HttpRequest request = parseRequestMessage(socket);

        // 2、创建一个最终用于返回的响应对象
        HttpResponse response = HttpResponse.builder()
                .protocol(request.getProtocol())
                .build();

        // 3、处理资源，顺序：动态 > 静态 > 404
        if (dynamicResource(request, response)
                || staticResource(request, response)) {
            response.ok();
        }
        else {
            // 404
            response.notFound();
        }
        // 4、返回响应给前端
        response(response);
    }

    // 调用该方法，就会向前端返回响应信息
    public void response(HttpResponse response) throws Exception {
        OutputStream outputStream = socket.getOutputStream();
        String message = response.toString();
        try {
            outputStream.write(message.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析参数字符串
     * param: parameter，参数
     * 1）根据&符号进行分割，拆出单组参数
     * 2）每组参数根据=符号分割，得到单独的参数名和参数值
     */
    private void parseParamStr(String paramStr, Map<String, List<String>> params) {
        // 拿到参数之后，首先进行字符解码工作
        paramStr = urlDecode(paramStr);
        // 开始根据参数字符串的特定格式进行拆分解析
        String[] split = paramStr.split("&");
        for (String s : split) {
            String[] split1 = s.split("=");
            String paramName = split1[0];
            // 考虑到参数名存在但是没有参数值的情况（提交了空参数值）
            String paramValue = "";
            if (split1.length > 1) {
                paramValue = split1[1];
            }
            // System.out.println("参数名：" + paramName
            //     + "，参数值：" + paramValue);

            List<String> valueList = params.get(paramName);
            if (valueList == null) {
                // 该参数是第一次出现
                valueList = new ArrayList<>();
                valueList.add(paramValue);
                params.put(paramName, valueList);
            } else {
                valueList.add(paramValue);
            }
        }
    }

    private String urlDecode(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return str; // 理论上大概率不会执行到
        }
    }

}
