package com.zhengwei.security;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan("com.zhengwei.security.mapper")
public class SecurityDemo {
    public static void main(String[] args) {
        // 调试->Evaluate(Alt+F8)->configurableApplicationContext.getBean(FilterChainProxy.class)获取filters列表
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(SecurityDemo.class, args);
        System.out.println("hello world");
    }
}
