package com.briup.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author YuYan
 * @date 2024-10-29 14:33:29
 * 在接口上面添加@ 表示这个接口也是一个可作为注解的接口：
 */
@Target(ElementType.TYPE)//表明这个注解可以使用在哪里（类，接口，方法）
@Retention(RetentionPolicy.RUNTIME)//注解保存的什么周期运行期间还是编译期间
public @interface WebHandler {
    String[] value();
}
