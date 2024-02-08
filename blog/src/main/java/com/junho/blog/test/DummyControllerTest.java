package com.junho.blog.test;


import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.junho.blog.model.RoleType;
import com.junho.blog.model.User;
import com.junho.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {
	
	@Autowired // 의존성 주입(DI)
	private UserRepository userRepository;
	
	//localhost:8080/blog/dummy/user/{id}
	// email,password 수정
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) { 
		// @RequestBody : body에 붙어온 json 데이터를 -> java Object 변환
		
		System.out.println("id : "+id);
		System.out.println("password : "+requestUser.getPassword());
		System.out.println("email : "+requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("수정에 실패하였습니다.");
			}
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		userRepository.save(user);
		// save메소드에 user 값이 프라이머리 키인 id값이 있을경우 update 
		// save메소드에 user 값에 id가 없을경우 insert를 합니다
		return null;
	}
	
	//localhost:8080/blog/dummy/user/page
	@GetMapping("/dummy/user/page")
	public Page<User> pageList(@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC) Pageable pageable){
		
		Page<User> pageUser = userRepository.findAll(pageable);
		// User의 정보만 나오게 하기 
		List<User> users = pageUser.getContent();
		return pageUser;
	}
	
	// {id} 주소로 파마레터를 전달 받을 수 있음
	// http://localhost:8080/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id ) { // 주소의 id값을 받아서 사용 할 수 있음
		// user/4을 찾으면 내가 데이터베이스에서 못찾아오게 되면 user가 null이 될 수 있음
		// 그럼 return null이 되니 프로그램 오류가 발생함.
		// Optional로 User 객체를 감싸서 가져오는 방식으로 null인지 아닌지 판단해서 return 하는 게 나을 것입니다.
		
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다. id : "+id);
			}
		});
		// 요청 : 웹브라우저
		// user 객체 = 자바 오브젝트
		// 변환 (웹브라이저가 이해할 수 있는 데이터) -> json(Gson 라이브러리)
		// 스프링부트 = MessageConverter라는 애가 응답시에 자동 작동
		// 만약에 자바 오브젝트를 리턴하게 되면 MessageConverter 가 Jackson 라이브러리를 호출해서
		// user 오브젝트를 json으로 변환해서 브라우저에게 던져줍니다.
		
		return user;
	}
	
	// http://localhost:8080/blog/dummy/join
	// http의 body에 username, password, email 데이터를 가지고 (요청)
	@PostMapping("/dummy/join")
	public String join(User user) { 
		System.out.println("id : "+user.getId());
		System.out.println("username : "+user.getUsername());
		System.out.println("password : "+user.getPassword());
		System.out.println("email : "+user.getEmail());
		System.out.println("role : "+user.getRole());
		System.out.println("createDate : "+user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		
		return "회원가입 성공";
	}
	
}
