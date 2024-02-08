package com.junho.blog.model;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Reply {
	
	@Id // Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auot_increment
	private int id;
	
	@Column(nullable=false, length = 200)
	private String content;
	
	@ManyToOne // Many = Reply, User = One
	@JoinColumn(name="boardId")
	private Board board;
	
	@ManyToOne // Many = Reply, User = One
	@JoinColumn(name="userId")
	private User user;
	
	@CreationTimestamp // 시간 자동 입력
	private Timestamp createDate;
	
	
	

}
