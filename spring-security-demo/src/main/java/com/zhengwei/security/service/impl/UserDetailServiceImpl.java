package com.zhengwei.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhengwei.security.domain.LoginUser;
import com.zhengwei.security.domain.SysUser;
import com.zhengwei.security.mapper.SysMenuMapper;
import com.zhengwei.security.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private SysUserMapper sysUserMapper;

	@Autowired
	private SysMenuMapper sysMenuMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 查询用户信息
		LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(SysUser::getUsername, username);
		SysUser sysUser = sysUserMapper.selectOne(queryWrapper);
		if (sysUser == null) {
			throw new UsernameNotFoundException("用户名不存在");
		}

		// 将sysUser封装成userDetails->LoginUser实现了UserDetails接口
        List<String> permissions = sysMenuMapper.selectPermsByUserId(sysUser.getId());
		return new LoginUser(sysUser, permissions);
	}
}
