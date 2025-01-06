package com.zhengwei.security.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser implements UserDetails {

	private SysUser sysUser;

	/**
	 * 获取权限信息
	 * @return
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.emptyList();
	}

	@Override
	public String getPassword() {
		return sysUser.getPassword();
	}

	@Override
	public String getUsername() {
		return sysUser.getUsername();
	}

	/**
	 * 账号是否过期
	 * @return
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * 账号是否锁定
	 * @return
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * 凭证是否过期
	 * @return
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * 账号是否启用
	 * @return
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}
}
