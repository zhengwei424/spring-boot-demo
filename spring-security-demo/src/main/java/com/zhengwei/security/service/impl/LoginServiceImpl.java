package com.zhengwei.security.service.impl;

import com.zhengwei.security.component.RedisCache;
import com.zhengwei.security.domain.LoginUser;
import com.zhengwei.security.domain.ResponseResult;
import com.zhengwei.security.domain.SysUser;
import com.zhengwei.security.service.LoginService;
import com.zhengwei.security.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

	@Override
	public ResponseResult login(SysUser sysUser) {
		// AuthenticationManager authenticate用户认证
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(sysUser.getUsername(), sysUser.getPassword());

		Authentication authentication = authenticationManager.authenticate(authenticationToken);
		// 如果认证没通过，给出相应提示
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("认证失败");
        }

		// 如果认证通过，使用userid 生成jwt 并存入ResponseResult返回

        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userid = loginUser.getSysUser().getId().toString();
        String jwt = JwtUtil.createJWT(userid);
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);
		// 把完成的用户信息存入redis， userid作为key
        redisCache.setCacheObject("login:" + userid, loginUser);

		return new ResponseResult(200, "登录成功", map);
	}
}
