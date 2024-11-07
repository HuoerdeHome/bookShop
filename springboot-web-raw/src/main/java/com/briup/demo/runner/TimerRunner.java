package com.briup.demo.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author YuYan
 * @date 2024-10-29 15:24:12
 */
// @Component
public class TimerRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        while (true) {
            System.out.println(new Date());
            Thread.sleep(1000);
        }
    }
}
