package com.junho.blog.service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.junho.blog.model.Board;
import com.junho.blog.model.User;
import com.junho.blog.repository.BoardRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardService {

	private ModelAndView mav;

	@Autowired
	private BoardRepository boardRepository;

	@Transactional
	public int write(Board board, User user) {
		board.setCount(0);
		board.setUser(user);

		try {
			// board.setRole(RoleType.USER);
			log.debug("boardRepository : write Board Info{}" + board);

			boardRepository.save(board);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("boardRepository : write  오류 메세지{}" + e.getMessage());
		}
		return -1;
	}

	@Transactional(readOnly = true)
	public ModelAndView boardList(Pageable pageable) {
		mav = new ModelAndView();

		Page<Board> boardList = boardRepository.findAll(pageable);

		int pageSize = 3;
		int blockSize = 5;
		int currPage = boardList.getPageable().getPageNumber() + 1;
		int totalPage = boardList.getTotalPages();
		int startPage = (int) (Math.floor(((currPage - 1) / blockSize)) * blockSize + 1);
		int endPage = (startPage + blockSize - 1) > totalPage ? totalPage : (startPage + blockSize - 1);

		Map<String, Integer> pageInfo = new HashMap<String, Integer>();

		pageInfo.put("pageSize", pageSize);
		pageInfo.put("blockSize", blockSize);
		pageInfo.put("currPage", currPage);
		pageInfo.put("totalPage", totalPage);
		pageInfo.put("startPage", startPage);
		pageInfo.put("endPage", endPage);

		log.info("============= pageInfo : {}", pageInfo);

		mav.addObject("boards", boardList);
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("index");

		return mav;
	}

	@Transactional(readOnly = true)
	public ModelAndView boardDetail(int id) {
		mav = new ModelAndView();

		Board board = boardRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 게시글은 없습니다. id : " + id);
			}
		});
		mav.addObject("board", board);
		mav.setViewName("board/boardDetail");

		return mav;
	}

	@Transactional
	public int boardDelete(int id) {
		try {
			log.debug("boardRepository : boardDelete{}" + id);
			boardRepository.deleteById(id);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("boardRepository : boardDelete  오류 메세지{}" + e.getMessage());
		}
		return -1;
	}

}
