package com.zhengwei.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


// https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
// https://github.com/spring-projects/spring-security/issues/11926
// https://www.baeldung.com/spring-deprecated-websecurityconfigureradapter
@Configuration
public class SecurityConfig {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().antMatcher("/user/login").anonymous();
//				.and().authorizeRequests().anyRequest().authenticated();
		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsService userDetailsService) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(userDetailsService).passwordEncoder(
				bCryptPasswordEncoder).and().build();
	}
}
