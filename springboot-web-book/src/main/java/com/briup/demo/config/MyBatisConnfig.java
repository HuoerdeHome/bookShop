package com.briup.demo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author 14409
 */
@Configuration//标记这个类为配置类
@MapperScan("com.briup.*.mapper")//映射路径
public class MyBatisConnfig {
    //这里是配置类：用来
}
