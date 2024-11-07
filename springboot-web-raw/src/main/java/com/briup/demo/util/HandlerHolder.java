package com.briup.demo.util;

import com.briup.demo.handler.Handler;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author YuYan
 * @date 2024-10-25 16:24:28
 */
public class HandlerHolder {

    private static final Map<String, Handler> handlerContainer = new HashMap<>();


    public static void addHandler(String url, Object handler) {
        handlerContainer.put(url.toLowerCase(), (Handler) handler);
    }

    static {
        try {
            // loadConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 根据URL获取对应处理器的方法
    public static Handler getHandler(String url) {

        // for (Map.Entry<String, Handler> entry : handlerContainer.entrySet()) {
        //     System.out.println(entry.getKey() + ", " + entry.getValue());
        // }

        return handlerContainer.get(url);
    }


    // 加载配置
    private static void loadConfig() throws Exception {
        // 临时创建一个Map集合，用来保存所有Handler在容器中的名称
        Map<String, Handler> namedHandlers = new HashMap<>();

        // 读取web.xml配置文件
        SAXReader reader = new SAXReader();
        Document doc = reader.read(ClassLoader.getSystemResourceAsStream("config/web.xml"));
        Element rootElement = doc.getRootElement();
        // 先读取所有的<handler>子元素
        List<Element> handlers = rootElement.elements("handler");
        if (handlers != null && handlers.size() > 0) {
            for (Element handler : handlers) {
                String handlerName = handler.element("handler-name").getText();
                String handlerClass = handler.element("handler-class").getText();
                Class<?> c = Class.forName(handlerClass);
                // 创建Handler对象
                Object instance = c.newInstance();
                namedHandlers.put(handlerName, (Handler) instance);
            }
        }

        List<Element> handlerMappings = rootElement.elements("handler-mapping");
        if (handlerMappings != null && handlerMappings.size() > 0) {
            for (Element handlerMapping : handlerMappings) {
                String handlerName = handlerMapping.element("handler-name").getText();
                String urlPattern = handlerMapping.element("url-pattern").getText();
                // 从保存handler名称的集合中取出handler对象
                Handler handler = namedHandlers.get(handlerName);
                handlerContainer.put(urlPattern.toLowerCase(), handler);
            }
        }
    }

}
