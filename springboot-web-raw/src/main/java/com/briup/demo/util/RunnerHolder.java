package com.briup.demo.util;

import org.springframework.boot.ApplicationArguments;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 保存所有的自启动任务
 *
 * @author YuYan
 * @date 2024-10-29 14:59:30
 */
public class RunnerHolder {

    private final static List<Class<?>> runnerContainer =
        new ArrayList<>();

    public static void addRunner(Class<?> cls) {
        runnerContainer.add(cls);
    }

    // 启动所有的自启动任务
    public static void run() {
        for (Class<?> c : runnerContainer) {
            try {
                // 对自启动类进行实例化
                Object runner = c.newInstance();
                // 获取到该类中的run()方法镜像
                Method runMethod = c.getDeclaredMethod("run", ApplicationArguments.class);
                // 启动一条单独的线程，通过方法的镜像，执行该方法即可
                new Thread(() -> {
                    try {
                        ApplicationArguments args = null;
                        runMethod.invoke(runner, args);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
