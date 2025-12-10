package com.donghyun.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;
/**
 * Description : BoardVO<br>
 * Date : 2025. 12. 5.<br>
 * History :<br>
 * - 작성자 : 이동현, 날짜 : 2025. 12. 5.<br>
 *
 * @author 이동현
 * @version 1.0
 */
@Data
public class BoardVO {
	private Long bno;
	private String title, content;
	private Date createAt, updateAt;
	
	// 사용자 정보
	private Long usersId;
	private String nickName, userImage;
	
	// 추가정보
	private int replyCount;

	// mapper.xml collection 태그를 통해 받아올 Board의 Image 파일명, Reply 데이터
	private List<String> images;
	private List<ReplyVO> replies;
}
	