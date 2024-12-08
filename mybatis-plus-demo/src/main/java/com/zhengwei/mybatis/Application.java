package com.zhengwei.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.zhengwei.mybatis")
@MapperScan("com.zhengwei.mybatis.dao")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
