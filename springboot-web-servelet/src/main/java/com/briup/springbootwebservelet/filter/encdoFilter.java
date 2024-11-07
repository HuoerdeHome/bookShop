package com.briup.springbootwebservelet.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author 14409
 *
 * 过滤器：当请求到达之后，就会被自动的调用
 *
 *
 */
@WebFilter("/*")
public class encdoFilter implements Filter {
    private final static String CHARACTER_ENCODING = "UTF-8";
    /**
     * 当请求到达之后，该方法会被自动调用
     *
     * 类似于Servlet中的service()方法
     * @param request 即将要提交给Servlet的请求对象
     * @param response 即将要提交给Servlet的响应对象
     * @param chain 调用链对象
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        System.out.println("doFilter()....");
        // 统一设置所有请求和响应的编码格式
        request.setCharacterEncoding(CHARACTER_ENCODING);
        response.setCharacterEncoding(CHARACTER_ENCODING);
        response.setContentType("charset=utf-8");
        // 将请求放行
        chain.doFilter(request, response);
    }
}
