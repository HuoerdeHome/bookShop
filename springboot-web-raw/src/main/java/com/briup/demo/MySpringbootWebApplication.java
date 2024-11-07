package com.briup.demo;

import com.briup.demo.annotation.WebHandler;
import com.briup.demo.util.HandlerHolder;
import com.briup.demo.util.RunnerHolder;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * @author YuYan
 * @date 2024-10-29 14:42:16
 */
public class MySpringbootWebApplication {

    public static void main(String[] args) {
        MySpringbootWebApplication.run(
                MySpringbootWebApplication.class);
    }

    public static void run(Class<?> c) {
        // 完整的递归起点：类加载路径下 + 包名前缀
        // 1、通过入口类的镜像获取到所在包名
        Package p = c.getPackage();
        String packageName = p.getName();
        // com.briup.demo
        System.out.println("packageName = " + packageName);
        // 把包名转换为路径表示内容，把"."转换为"/"
        String packagePath = packageName.replaceAll("\\.", "/");
        System.out.println("packagePath = " + packagePath);

        // 2、通过类加载器读取当前类加载路径的完整名称
        URL url = ClassLoader.getSystemResource("");
        String classPath = url.getFile();
        try {
            classPath = URLDecoder.decode(classPath, StandardCharsets.UTF_8.displayName());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("classPath = " + classPath);

        // 3、创建一个File对象，表示递归扫描的起点
        File rootDir = new File(classPath, packagePath);
        System.out.println(rootDir.exists());

        // 将扫描根路径，传入递归方法作为起点，开始扫描
        recursion(packageName, rootDir);

        // 当整个递归结束之后，开始将所有的自启动任务全部运行起来
        RunnerHolder.run();
    }


    public static void recursion(String packageName, File dir) {
        // 拉取到dir下的所有子文件
        File[] files = dir.listFiles();
        // 设置递归出口
        if (files == null || files.length == 0) {
            return;
        }
        // 遍历所有子文件
        for (File file : files) {
            // 读取出文件/目录的名称
            String fileName = file.getName();

            // 1）file是一个.class字节码文件
            // 打印输出这个类名（暂时）
            if (fileName.endsWith(".class")) {
                // System.out.println(fileName);
                resolveClass(packageName, fileName);
            }
            // 2）是当前包下的一个子包
            // 继续递归调用，将子包目录传入
            else if (file.isDirectory()) {
                String childPackageName = packageName + "." + fileName;
                recursion(childPackageName, file);
            }
        }
    }

    public static void resolveClass(String packageName, String classFileName) {
        // Calculator.class
        // 将文件名中的".class"去掉
        String[] split = classFileName.split("\\.");
        String simpleClassName = split[0];
        // 将包名和类名使用小数点拼接在一起，就得到了完全限定性类名
        String fullClassName = packageName + "." + simpleClassName;
        // System.out.println("fullClassName = " + fullClassName);
        // 根据完全限定性类名获取该类的镜像
        Class<?> c;
        try {
            c = Class.forName(fullClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        // 1、判断该类是否添加了WebHandler注解
        WebHandler webHandler = c.getAnnotation(WebHandler.class);
        if (webHandler != null) {
            try {
                // 1.1、把Handler对象创建出来
                Object handler = c.newInstance();
                // 1.2、读取出注解中配置的value值（给这个Handler配置的所有映射路径）
                String[] value = webHandler.value();
                if (value.length > 0) {
                    for (String url : value) {
                        HandlerHolder.addHandler(url, handler);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 2、扫描自启动任务组件
        // 扫描哪些类中的方法是需要项目启动的时候自动运行
        // 规则：1）添加了@Component注解
        //      2）实现了ApplicationRunner接口
        // 启动的时候自动调用该类中的run()方法
        // 具体实施方式：把所有符合要求的类，存入到一个集合中
        // 等整个递归扫描全部结束之后，再依次启动这些类运行
        Component component = c.getAnnotation(Component.class);
        if (component != null) {
            // 2.1、判断该类型是否实现了ApplicationRunner接口
            // 获取到该类型实现的所有的接口
            Class<?>[] interfaces = c.getInterfaces();
            // 设置一个变量，用来记录是否包含指定接口
            boolean result = false;
            for (Class<?> anInterface : interfaces) {
                if (anInterface == ApplicationRunner.class) {
                    result = true;
                    break;
                }
            }
            // 当循环结束之后，如果值变为了true，就说明：
            // 当前这一轮递归扫描到的这个类型是实现了ApplicationRunner接口的
            if (result) {
                RunnerHolder.addRunner(c);
            }
        }
    }


}
