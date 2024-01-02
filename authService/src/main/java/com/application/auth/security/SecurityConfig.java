package com.application.auth.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity //Spring Security를 적용한다는 어노테이션
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final ExService exService;
	/**
	 * 규칙 설정
	 * @param http
	 * @throws Exception
	 * 
	 * */
	
	@Override
	public void configure(HttpSecurity http) throws Exception{
	//configure(HttpSecurity http): 이 메소드는 HttpSecurity 객체를 이용해 각 요청을 먼저 intercept(가로챈다)하여 .URL별 인증 여부,
	//login처리, logout아웃 처리등 다양한 처리를 할 수 있다.
		
		
		http.authorizeRequests()
		.antMatchers("/userAccess").hasRole("USER")
		// antMachers : 각 URL 요청에 대한 접근 여부를 설정, 위 같은 경우 /userAccess에 접근할 경우 hasRole()을 통해 USER라는 권한을 가진 유저만 접근 가능
		.antMatchers("/signUp").anonymous() //anonymous()은 인증되지 않은 즉, 로그인 되지 않은 사용자만 접근 가능
		.and()
		.formLogin() //Spring Security에서 제공하는 login form을 이용한다는 뜻, 로그인 성공시 "/"로 리다이렉트
		.and()
		.csrf().disable(); //웹 사이트의 취약점을 이용한 의도치 않은 요청을 통한 공격을 의미. 이 기능을 disable한 상태.
		
	}
	
	/**
	 * 로그인 인증 처리 메소드
	 * @param auth
	 * @throws Exception
	 * */
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		//configure(AuthenticationManagerBuilder auth): 이 메소드는 AuthenticationManagerBuilder 객체를 통해 인증 객체 생성 기능을 제공.
		//exService는 뒤에서 다룰 UserDetails 인터페이스를 상속받은 객체이고 이것을 이용해 로그인 된 사용자의 데이터를 관리한다.
		//passwordEncoder()는 로그인때 입력한 패스워드를 암호화 처리한다.
		auth.userDetailsService(exService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
}
