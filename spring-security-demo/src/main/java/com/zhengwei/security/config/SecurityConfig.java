package com.zhengwei.security.config;

import com.zhengwei.security.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


// https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
// https://github.com/spring-projects/spring-security/issues/11926
// https://www.baeldung.com/spring-deprecated-websecurityconfigureradapter
@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf().disable() // 关闭csrf
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 不通过session获取securitycontext
				.and().authorizeRequests()
                .antMatchers("/user/login").anonymous()// 登录接口允许匿名访问->anonymous()匿名访问指未认证登录的用户可以访问这个url，已经登录过的用户不允许访问该url
                .anyRequest().authenticated(); //其他请求需要认证

        // 设置jwt过滤器的位置(该配置是将jwtAuthenticationTokenFilter放在UsernamePasswordAuthenticationFilter之前
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsService userDetailsService) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(userDetailsService).passwordEncoder(
				bCryptPasswordEncoder).and().build();
	}
}
