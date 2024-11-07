package com.briup.springbootwebservelet.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author 14409
 *
 */
@MapperScan("com.briup.springbootwebservelet.mapper")//注解配置类路径
@Configuration//表明配置类
public class myBatisConfig {


}
