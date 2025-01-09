package com.zhengwei.security.handler;

import com.alibaba.fastjson.JSON;
import com.zhengwei.security.domain.ResponseResult;
import com.zhengwei.security.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理授权异常
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
		ResponseResult<?> result = new ResponseResult<>(HttpStatus.FORBIDDEN.value(), "权限不足");
		String resultJson = JSON.toJSONString(result);
		WebUtils.renderString(response, resultJson);
	}
}
