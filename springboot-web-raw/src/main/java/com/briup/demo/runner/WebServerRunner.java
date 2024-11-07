package com.briup.demo.runner;

import com.briup.demo.util.ClientThread;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author YuYan
 * @date 2024-10-24 10:08:27
 */
@Component
public class WebServerRunner implements ApplicationRunner {
    // 这个方法会在Spring项目启动的时候被自动调用一次
    @Override
    public void run(ApplicationArguments args) throws Exception {
        int port = 8088;
        ServerSocket ss = new ServerSocket(port);
        System.out.println("服务器已启动！端口号：" + port);
        while (true) {
            // 接收一个客户端连接
            Socket socket = ss.accept();
            // 启动一条客户端线程，处理这次请求
            new ClientThread(socket).start();
        }
    }
}
