package com.junho.blog.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.junho.blog.model.User;
import com.junho.blog.repository.UserRepository;
import com.junho.blog.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PrincipalDetailService implements UserDetailsService	{
	
	@Autowired
	private UserRepository userRepository;
	
	// 스프링이 로그인 요청을 가로챌 때, email, password 변수 2개를 가로채는데
	// password 부분 처리는 알아서함. 
	// email 만 DB에 있는지 확인해주면 됨.
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("qweqwe");
		log.info("loadUserByUsername 진입 username = {}",username);
		User user = userRepository.findByEmail(username);
		return new PrincipalDetail(user); // 시큐리티 세션에 유저 정보가 저장됨.
	}

}
