package com.junho.blog.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.junho.blog.dto.ResponseDto;
import com.junho.blog.model.Board;
import com.junho.blog.model.User;
import com.junho.blog.service.BoardService;
import com.junho.blog.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserApiController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> join(@RequestBody User user) {
		log.info("Join write {}", user);
		int result = userService.join(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), result);
	}

}
