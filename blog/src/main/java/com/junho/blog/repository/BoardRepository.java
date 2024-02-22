package com.junho.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.junho.blog.model.Board;
import com.junho.blog.model.User;


public interface BoardRepository extends JpaRepository<Board, Integer> { // JpaRepository<테이블,프라이머리 키 타입>

}
