package com.junho.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
//스프링이 com.junho.blog 패키이 이하를 스캔해서
// 모든 파일을 메모리에 new 해서 하는 건 아니고
// 특정 어느테이션이 붙어있는 클래스 파일들을 new해서(IoC) 스프링
// 컨테이너에 관리해줍니다.
public class blogControllerTest {
	
	// http://localhost/test/hello
	@GetMapping("/test/hello")
	public String hello() {
		return "<h1>Hello spring boot</h1>";
	}
	
}