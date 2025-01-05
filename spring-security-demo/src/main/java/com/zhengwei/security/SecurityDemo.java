package com.zhengwei.security;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zhengwei.security.mapper")
public class SecurityDemo {
    public static void main(String[] args) {
        SpringApplication.run(SecurityDemo.class, args);
    }
}
