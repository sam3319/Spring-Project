package com.donghyun.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donghyun.domain.BoardVO;
import com.donghyun.mapper.BoardMapper;

import lombok.extern.log4j.Log4j;

/**
 * Description : BoardService<br>
 * Date : 2025. 12. 6.<br>
 * History :<br>
 * - 작성자 : 이동현, 날짜 : 2025. 12. 6.<br>
 *
 * @author 이동현
 * @version 1.0
 */
@Service
@Log4j
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper mapper;
	
	// 모든 게시글 조회
	@Override
	public List<BoardVO> getBoardList(int page, int size) {
		int offset = (page - 1) * size;
		
		return mapper.getAllBoardPaging(offset, size);
	}

	// 게시글 상세조회
	@Override
	public BoardVO getBoard(Long bno) {
		  log.info("===== getBoard 호출 =====");
	        
	      BoardVO board = mapper.getBoard(bno);
	        
	      return board;
	}

	@Override
	public void insertBoard(BoardVO board) {
		log.info("===== 게시글 등록 시작 =====");
		log.info(board);
		mapper.insertBoard(board);
		
		if(board.getImages() != null && !board.getImages().isEmpty()) {
			mapper.insertBoardImages(board.getBno(), board.getImages());
		}
	}

	@Override
	public void modifyBoard(BoardVO board) {
		log.info("===== 게시글 수정 시작 =====");
		mapper.modifyBoard(board);
	}
	
	@Override
	public void removeBoard(Long bno) {
		log.info("===== 게시글 삭제 시작 =====");
		mapper.removeBoard(bno);
	}

	@Override
	public List<Map<String, Object>> getUserPostThumbnails(Long usersId) {
	    return mapper.getUserPostThumbnails(usersId);
	}

	@Override
	public int getUserPostCount(Long usersId) {
	    return mapper.getUserPostCount(usersId);
	}
}
