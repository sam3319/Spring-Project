package com.donghyun.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.donghyun.domain.BoardVO;
import com.donghyun.service.BoardService;

import lombok.extern.log4j.Log4j;

/**
 * Description : BoardRestController<br>
 * Date : 2025. 12. 8.<br>
 * History :<br>
 * - 작성자 : 이동현, 날짜 : 2025. 12. 8.<br>
 *
 * @author 이동현
 * @version 1.0
 */
@RestController
@RequestMapping("/api/board")
@Log4j
public class BoardRestController {
	
	@Autowired
	public BoardService service;
	
	// 게시글 전체 조회
	@GetMapping("/list")
	public ResponseEntity<List<BoardVO>>getBoardList(@RequestParam(defaultValue="1")int page, @RequestParam(defaultValue="5")int size){
		List<BoardVO>board = service.getBoardList(page, size);
		
		
		return ResponseEntity.ok(board);
	}
	
	// 게시글 상세 조회
	@GetMapping("/{bno}")
	public ResponseEntity<BoardVO> getBoardDetail(@PathVariable Long bno) {
	    BoardVO board = service.getBoard(bno);
	        
	    return ResponseEntity.ok(board);
	}
}
