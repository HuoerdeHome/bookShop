package com.briup.springbootwebservelet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan("com.briup.springbootwebservelet")
public class SpringbootWebServeletApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWebServeletApplication.class, args);
    }

}
