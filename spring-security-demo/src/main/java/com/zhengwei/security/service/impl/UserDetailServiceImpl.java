package com.zhengwei.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhengwei.security.domain.LoginUser;
import com.zhengwei.security.domain.SysUser;
import com.zhengwei.security.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private SysUserMapper sysUserMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 查询用户信息
		LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(SysUser::getUsername, username);
		SysUser sysUser = sysUserMapper.selectOne(queryWrapper);
		if (sysUser == null) {
			throw new UsernameNotFoundException("用户名不存在");
		}
		// TODO: 查询对应权限信息


		// 将sysUser封装成userDetails->LoginUser实现了UserDetails接口
		return new LoginUser(sysUser);
	}
}
