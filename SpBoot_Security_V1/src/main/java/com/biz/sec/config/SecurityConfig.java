package com.biz.sec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.SpringServletContainerInitializer;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

import com.biz.sec.service.SecurityUserServiceImpl;

import lombok.RequiredArgsConstructor;

/*
 * spring security의 customizeing 첫번째 단계
 * security-context.xml에 설정했던 옵션들을 지정하는 클래스
 */
@RequiredArgsConstructor
@Configuration
//@EnableWebSecurity // 선택사항
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	private final SecurityUserServiceImpl suService;

	/*
	 * 초기값 설정
	 */
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// TODO Auto-generated method stub
		// super.configure(http);
		httpSecurity.authorizeRequests()
		.antMatchers("/hello").permitAll()
		.antMatchers("/login").permitAll()
		.antMatchers("/admin").hasRole("ADMIN")
		.antMatchers("/user/login").permitAll()
		.antMatchers("/**").permitAll();
		
		httpSecurity.authorizeRequests()
		.and()
		.formLogin()
		.loginPage("/user/login")
		.failureUrl("/user/login?error")
		.usernameParameter("username")
		.passwordParameter("password")
		.and().logout().logoutSuccessUrl("/");
	}
	
	
	
	/*
	 * AuthenticationManagerBuilder auth =:> AuthenticationProvider 역할을 함
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		// super.configure(auth);
		auth.userDetailsService(suService)
		.passwordEncoder(suService.getPasswordEncoder());
	}

	/*
	 *  <bean></bean>을 대신하는 java 설정
	 * *.html templates 파일에서 sec: tag를 상용할 때
	 * 값, 설정, 함수 등을 사용할 수 있도록 객체를 전달하는 용도
	 * 
	 * thymeleaf-extras-springSecurity5에서는
	 * security 설정된 config에서 자동 주입이 된다
	 * 특히 Config class를  WebSecurityConfigurerAdapter 상속받아서
	 * 작성할 경우 굳이 bean으로 설정하지 않아도 된다
	 */
	@Bean
	public SpringSecurityDialect springSecurityDialect() {
		return new SpringSecurityDialect();
	}



	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(web);
		web.ignoring()
			.antMatchers("/static/css/**","/static/js/**")
			.antMatchers("/static/images/**")
			.antMatchers("/static/music/**");
	}
	
}
