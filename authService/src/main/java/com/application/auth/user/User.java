package com.application.auth.user;

import lombok.Data;

@Data
public class User {
	
	private Long id; 
	private String email; //이메일
	private String password; //패스워드
	private String role; //권한(role에는 "USER"라는 값이 들어가게된다.)
	
	
}
