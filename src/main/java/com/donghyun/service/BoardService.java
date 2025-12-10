package com.donghyun.service;

import java.util.List;
import java.util.Map;

import com.donghyun.domain.BoardVO;

/**
 * Description : BoardService<br>
 * Date : 2025. 12. 6.<br>
 * History :<br>
 * - 작성자 : 이동현, 날짜 : 2025. 12. 6.<br>
 *
 * @author 이동현
 * @version 1.0
 */
public interface BoardService {
	
	// 게시판 정보를 모두 가져오기
	public List<BoardVO> getBoardList(int page, int size);
	
	// 하나의 게시판 정보를 가져오기
	public BoardVO getBoard(Long bno);
	
	// 게시글 작성
	public void insertBoard(BoardVO board);
	
	// 게시글 수정
	public void modifyBoard(BoardVO board);
	
	// 게시글 삭제
	public void removeBoard(Long bno);
	
	// 게시글 썸네일 가져오기
	public List<Map<String,Object>> getUserPostThumbnails(Long usersId);
	
	// 총 게시글 수 가져오기
	public int getUserPostCount(Long usersId);
}
