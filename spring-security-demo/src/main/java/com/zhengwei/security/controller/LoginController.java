package com.zhengwei.security.controller;

import com.zhengwei.security.domain.ResponseResult;
import com.zhengwei.security.domain.SysUser;
import com.zhengwei.security.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;

	@PostMapping("/user/login")
	public ResponseResult<?> login(@RequestBody SysUser sysUser) {

		return loginService.login(sysUser);
	}

	@RequestMapping("/user/logout")
	public ResponseResult<?> loginout() {

		return loginService.logout();
	}
}
