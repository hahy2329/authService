package com.application.auth.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.application.auth.user.User;

/*
 * SecurityConfig클래스에서 exService객체는 UserDetails인터페이스를 상속받는다고 했습니다.
 * Spring Security는 사용자 정보를 UserDetails라는 인터페이스 구현체(MyUserDetail)로 사용한다. 즉, UserDetails는 사용자 정보인 VO라고
 * 생각하면 됩니다.
 * 
 * */

public class MyUserDetail implements UserDetails {
	
	private String email;
	private String password;
	private String auth;
	
	public MyUserDetail(User user) {
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.auth = "ROLE_" + user.getRole();
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority(this.auth));
	} //계정이 가지고 있는 권한 목록 반환

	@Override
	public String getPassword() {
		return this.password;
	}//계정의 비밀번호 반환

	@Override
	public String getUsername() {
		return this.email;
	}//계정의 이름 반환

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}//계정 만료 여부(false = 만료)

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}// 계정 잠김 여부(false = 잠김)

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}//계정 비밀번호 만료 여부(false = 만료)

	@Override
	public boolean isEnabled() {
		return true;
	}//계정 활성화 여부(false = 비활성)

}
