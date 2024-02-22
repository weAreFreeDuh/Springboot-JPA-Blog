package com.junho.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

// 빈 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것
@Configuration // 빈등록 (IoC)
@EnableWebSecurity // 스프링 시큐리티가 활성화가 되어있는데, 시큐리티 설정을 이 파일에서 하겠다는 의미.
public class SecurityConfig {

	@Bean
	BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}

	// 시큐리티가 대신 로그인해주는데 password를 가로채기를 하는데
	// 해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
	// 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있음.

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		// antMatchers 부분도 deprecated 되어 requestMatchers로 대체
		return (web) -> web.ignoring().requestMatchers("/js/**", "/css/**", "/image/**","/dummy/**");
	}

	@SuppressWarnings("removal")
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
		requestCache.setMatchingRequestParameterName("null");
		
		  http
		  	.csrf().disable() // csrf 토큰 비활성화 (테스트시 걸어두면 좋음)
		  	  .authorizeHttpRequests()	// 요청이 들어오면
              .requestMatchers("/auth/**","/WEB-INF/**") // 해당 위치로 들어오면
              .permitAll() // 누구나 들어올 수 있다.
              .anyRequest() // 다른 요청은
              .authenticated(); // 인증이 되어야한다.
		  
          http
              .formLogin()
              .loginPage("/auth/loginForm")
              .loginProcessingUrl("/auth/loginProc")  // 스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대시 로그인해줍다
              .defaultSuccessUrl("/")
              .permitAll();
		
		return http.build();
	}

}