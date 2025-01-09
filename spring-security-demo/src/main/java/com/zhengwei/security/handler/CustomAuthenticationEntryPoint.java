package com.zhengwei.security.handler;


import com.alibaba.fastjson.JSON;
import com.zhengwei.security.domain.ResponseResult;
import com.zhengwei.security.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理认证异常
 */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
		ResponseResult<?> result = new ResponseResult<>(HttpStatus.UNAUTHORIZED.value(), "用户认证失败");
		String resultJson = JSON.toJSONString(result);
		WebUtils.renderString(response, resultJson);
	}
}