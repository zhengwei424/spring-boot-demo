package com.zhengwei.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {

    @RequestMapping("/hello")
    @PreAuthorize(value = "hasAuthority('sys:test:list')") // 能访问
    public String hello() {
        return "hello";
    }

    @RequestMapping("/admin")
    @PreAuthorize(value="hasAuthority('sys:dept:list')") // 能访问
    public String admin() {
        return "admin";
    }

    @RequestMapping("/hello1")
    @PreAuthorize(value = "hasAuthority('aaa')") // 不能访问
    public String hello1() {
        return "hello1";
    }
}
