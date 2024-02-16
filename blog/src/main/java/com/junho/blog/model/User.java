package com.junho.blog.model;




import java.sql.Timestamp;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
// ORM -> java(다른 언어) -> 언어로 만든 변수들을 테이블로 매핑시켜주는것.
@Entity // User 클래스가 MySQL에 테이블이 생성이 된다.
//@DynamicInsert // insert시에 null인 필드를 제외시켜줍니다
public class User {
	
		@Id // Primary Key
		@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
		private int id; // 시퀀스, auto_increment
		
		@Column(nullable=false, length = 30,unique=true)
		private String username; // 아이디
		
		@Column(nullable=false, length = 100)
		private String password;
		
		@Column(nullable=false, length = 30)
		private String email;
		
		@ColumnDefault("'user'")
		// DB에는 RoleType이라는 게 없기때문에 문자열이라는걸 인식시켜줍니다.
		@Enumerated(EnumType.STRING)
		private RoleType role; 
		
		@CreationTimestamp // 시간 자동 입력
		private Timestamp createDate;
		
}
