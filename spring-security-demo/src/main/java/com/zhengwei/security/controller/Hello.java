package com.zhengwei.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {

    @RequestMapping("/hello")
    @PreAuthorize(value = "hasAuthority('test')") // 能访问
    public String hello() {
        return "hello";
    }

    @RequestMapping("admin")
    @PreAuthorize(value="hasAuthority('admin')") // 能访问
    public String admin() {
        return "admin";
    }

    @RequestMapping("hello1")
    @PreAuthorize(value="hasAuthority('hello')") // 不能访问->不存在hello权限
    public String hello1() {
        return "hello1";
    }
}
