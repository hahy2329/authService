package com.application.auth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.application.auth.security.MyUserDetail;
import com.application.auth.user.User;

import lombok.RequiredArgsConstructor;

/*
 * UserDetailService 인터페이스는  DB에서 유저 정보를 불러오는 메서드인 loadUserByUsername()이 있다. 이 메소드
 * 구현을 통해 DB에서 유저 정보를 불러온다.
 * 
 * */


@Service
@RequiredArgsConstructor
public class ExService implements UserDetailsService {
	
	private final ExRepository repository;

	public void joinUser(User user) {
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		repository.saveUser(user);
		
	}
	
	//repository 만들기
	
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = repository.findUserByEmail(email); //email인자는 loginForm에서 입력 받은 email을 받는다.
		return new MyUserDetail(user);
	}
	
	
}
