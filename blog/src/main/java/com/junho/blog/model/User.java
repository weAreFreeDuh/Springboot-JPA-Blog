package com.junho.blog.model;

import java.security.Timestamp;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// ORM -> java(다른 언어) -> 언어로 만든 변수들을 테이블로 매핑시켜주는것.
@Entity // User 클래스가 MySQL에 테이블이 생성이 된다.
public class User {
	
		@Id // Primary Key
		@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
		private int id; // 시퀀스, auto_increment
		
		@Column(nullable=false, length = 30)
		private String username; // 아이디
		
		@Column(nullable=false, length = 30)
		private String password;
		
		@Column(nullable=false, length = 30)
		private String email;
		
		@ColumnDefault("'user'")
		private String role; // Enum을 쓰는게 좋다. // admin, user, manager
		
		@CreationTimestamp // 시간 자동 입력
		private Timestamp createData;
		
}
