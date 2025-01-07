package com.zhengwei.security.domain;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser implements UserDetails {

	private SysUser sysUser;

    // 存储权限信息
    private List<String> permissions;

    @JSONField(serialize = false) // 存入redis时，忽略该字段
    private List<GrantedAuthority> authorities;

    public LoginUser(SysUser sysUser, List<String> permissions) {
        this.sysUser = sysUser;
        this.permissions = permissions;
    }


	/**
	 * 获取权限信息
	 * @return
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
        // 将permission对象转换成GrantedAuthority对象，并返回
		return permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
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
