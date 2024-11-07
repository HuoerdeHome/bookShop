package com.briup.demo.webserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 手动搭建一个Web服务器端，接收浏览器发送的请求报文
 *
 * @author YuYan
 * @date 2024-10-24 09:38:14
 */
public class WebServerDemo {
    public static void main(String[] args) throws Exception {
        // 创建服务器套接字对象
        int port = 8088;
        ServerSocket ss = new ServerSocket(port);
        System.out.println("服务器已启动！端口号：" + port);
        while (true) {
            Socket socket = ss.accept();
            new Thread() {
                @Override
                public void run() {
                    try {
                        run0();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                void run0() throws Exception {
                    System.out.println("有客户端连接了服务器！");
                    // 按照HTTP协议的规则，客户端率先向服务器发送报文
                    InputStream is = socket.getInputStream();
                    // 使用io流读取数据（浏览器发送的请求报文信息）
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                int i;
                                while ((i = is.read()) != -1) {
                                    System.out.print((char) i);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                     new Thread() {
                         @Override
                         public void run() {
                             try {
                                 OutputStream os = socket.getOutputStream();
                                 // String body = "Hi, Browser!";
                                 String body = "<html>" +
                                         "<head>" +
                                         "<script src='/js/ajax.js'></script>" +
                                         "</head>" +
                                         "<body>" +
                                         "<h1>Hi!!!</h1>" +
                                         "</body>" +
                                         "</html>";
                                 String msg = "HTTP/1.1 200 OK\n"
                                         + "Content-Type: text/html,charset=utf-8\n"
                                         + "Content-Length: " + body.getBytes().length + "\n"
                                         + "\n"
                                         + body;
                                 os.write(msg.getBytes());
                                 os.flush();
                             } catch (IOException e) {
                                 e.printStackTrace();
                             }
                         }
                     }.start();
                }
            }.start();
        }
    }
}
