package com.biz.sec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UtilesConfig {

	@Bean
	public PasswordEncoder passwordEncorder() {
		return new BCryptPasswordEncoder();
	}
	
}
