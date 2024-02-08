package com.junho.blog.model;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auot_increment
	private int id; // 키값
	
	@Column(nullable=false,length=100)
	private String  title; // 제목
	
	@Column(columnDefinition = "longtext") // 대용량 데이터
	private String  content; //섬머노트 라이브러리 <html>태그가 섞여서 디자인이 됨.
	
	@ColumnDefault("0")
	private int count; // 조회수
	
	@ManyToOne // Many = board, User = One
	@JoinColumn(name="userId")
	private User user; //DB는 오브젝트를 저장할 수 없다. // 자바는 오브젝트를 저장할 수 있다. // FK
	
	@OneToMany(mappedBy="board",fetch = FetchType.LAZY) // mappedBy 연과관계의 주인이 아니다 (난 FK가 아니예요) DB에 칼럼을 만들지 마세요
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate; // 생성일
}
