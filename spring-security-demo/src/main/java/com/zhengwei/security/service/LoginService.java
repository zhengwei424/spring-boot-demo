package com.zhengwei.security.service;

import com.zhengwei.security.domain.ResponseResult;
import com.zhengwei.security.domain.SysUser;

public interface LoginService {
	ResponseResult<?> login(SysUser sysUser);

	ResponseResult<?> logout();
}
