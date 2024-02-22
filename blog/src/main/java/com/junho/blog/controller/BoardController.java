package com.junho.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.junho.blog.auth.PrincipalDetail;
import com.junho.blog.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {

	private ModelAndView mav;

	@Autowired
	private BoardService boardService;
	
	@GetMapping("/board/writeForm")
	public String writeForm() {
		return "board/writeForm";
	}

	@GetMapping({ "/", "" })
	public ModelAndView index(@AuthenticationPrincipal PrincipalDetail principal,
			@PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		mav = boardService.boardList(pageable);
		return mav;
	}

	@GetMapping("/board/{id}")
	public ModelAndView boardDetail(@PathVariable int id) {
		mav = boardService.boardDetail(id);
		return mav;
	}
	
	@GetMapping("/board/modify/{id}")
	public ModelAndView boardModify(@PathVariable int id) {
		mav = boardService.boardDetail(id);
		mav.setViewName("board/boardModify");
		return mav;
	}

	
}
