package com.junho.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.junho.blog.model.RoleType;
import com.junho.blog.model.User;
import com.junho.blog.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public int join(User user) {
		try {
			user.setRole(RoleType.USER);
			log.debug("userRepository : join 회원가입 User Info{}"+ user);
			userRepository.save(user);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("userRepository : join 회원가입 오류 메세지{}"+ e.getMessage());
		}
		return -1;
	}
	
	@Transactional(readOnly = true) // Select 할 때 트랜잭션 시작, 서비스 종료시에 트렌잭션 종료 (정합성 높여줍니다)
	public User login(User user) {
		return userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword()); 
	}

}
