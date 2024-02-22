package com.junho.blog.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.junho.blog.auth.PrincipalDetail;
import com.junho.blog.dto.ResponseDto;
import com.junho.blog.model.Board;
import com.junho.blog.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BoardApiController {
	
	@Autowired
	private BoardService boardService;
	
	@PostMapping("/api/board/write")
	public ResponseDto<Integer> write(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
		log.info("Join write {}", board);
		int result = boardService.write(board, principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(), result);
	}

	@DeleteMapping("/api/board/delete")
	public ResponseDto<Integer> boardDelete(@RequestBody int id) {
		log.info("============= boardDelete id {}", id);
		int result = boardService.boardDelete(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), result);
	}
	
}
