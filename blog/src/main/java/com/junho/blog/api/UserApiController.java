package com.junho.blog.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.junho.blog.dto.ResponseDto;
import com.junho.blog.model.User;
import com.junho.blog.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j

public class UserApiController {

	@Autowired
	private UserService userService;

	@PostMapping("/api/user/join")
	public ResponseDto<Integer> join(@RequestBody User user) {
		log.info("Join User {}", user);
		int result = userService.join(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), result);
	}

	@PostMapping("/api/user/login")
	public ResponseDto<Integer> login(@RequestBody User user, HttpSession session) {
		log.info("login User {}", user);
		User principal = userService.login(user); // principal (접근주체)
		log.info("login User after service{}", user);
		int result = -1;
		if(principal.getEmail() != null && principal.getPassword() != null) {
			session.setAttribute("principal", principal); // 세션 값 생성
			result = 1;
		}

		return new ResponseDto<Integer>(HttpStatus.OK.value(), result);
	}

}
