package com.application.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.application.auth.service.ExService;
import com.application.auth.user.User;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ExController {
	private final ExService exService;
	
	
	/**
	 * 회원가입 폼
	 * @return
	 * 
	 * */
	@GetMapping("/signUp")
	public String signUpForm() {
		return "signUp";
	}
	
	
	/**
	 * 회원가입 진행
	 * @param user
	 * @return
	 * */
	@PostMapping("/signUp")
	public String signUp(User user) {
		user.setRole("USER");
		exService.joinUser(user);
		return "redirect:/login";
	}
	
}
