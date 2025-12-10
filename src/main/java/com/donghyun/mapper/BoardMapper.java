package com.donghyun.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.donghyun.domain.BoardVO;

/**
 * Description : BoardMapper<br>
 * Date : 2025. 12. 7.<br>
 * History :<br>
 * - 작성자 : 이동현, 날짜 : 2025. 12. 7.<br>
 *
 * @author 이동현
 * @version 1.0
 */
public interface BoardMapper {
	
	// 게시글 전체 가져오기
	public List<BoardVO> getAllBoardPaging(@Param("offset")int offset, @Param("size")int size);
	
	// 게시글 상세 조회
	public BoardVO getBoard(Long bno);
	
	// 게시글 작성
	public void insertBoard(BoardVO board);
	
	// 게시글 수정
	public void modifyBoard(BoardVO board);
	
	// 게시글 삭제
	public void removeBoard(Long bno);
	
	// 이미지 등록
	public void insertBoardImages(@Param("bno")Long bno, @Param("images")List<String> images);
	
	// 썸네일 조회
	public List<Map<String, Object>> getUserPostThumbnails(Long usersId);
	
	// 총 게시글 수 조회
	public int getUserPostCount(Long usersId);
}
