package com.zhengwei.security.service.impl;

import com.zhengwei.security.domain.ResponseResult;
import com.zhengwei.security.domain.SysUser;
import com.zhengwei.security.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public ResponseResult login(SysUser sysUser) {
		// AuthenticationManager authenticate用户认证
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(sysUser.getUsername(), sysUser.getPassword());

		Authentication authentication = authenticationManager.authenticate(authenticationToken);
		// 如果认证没通过，给出相应提示

		// 如果认证通过，使用userid 生成jwt 并存入ResponseResult返回

		// 把完成的用户信息存入redis， userid作为key


		return null;
	}
}
