package com.junho.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.junho.blog.model.User;

//DAO
// 자동으로 bean 등록이 된다.
// @Repository 생략가능
// 기본적인 CRUD 가 가능하다.
public interface UserRepository extends JpaRepository<User, Integer> { // JpaRepository<테이블,프라이머리 키 타입>
	
	// SELECT * FROM user WHERE username = ?
	User findByEmail(String username);

	// JPA Naming 쿼리 전략
	// Select * FROM user WHERE email = ? AND password = ?;
	// User findByEmailAndPassword(String email, String password);

	// @Query(value="Select * FROM user WHERE email = ? AND password = ?",
	// nativeQuery = true)
	// User login(String email, String password);
}
