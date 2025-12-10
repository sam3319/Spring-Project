package com.donghyun.domain;

import java.util.Date;

import lombok.Data;

/**
 * Description : ReplyVO<br>
 * Date : 2025. 12. 5.<br>
 * History :<br>
 * - 작성자 : 이동현, 날짜 : 2025. 12. 5.<br>
 *
 * @author 이동현
 * @version 1.0
 */
@Data
public class ReplyVO {

	private Long rno, bno;
	private String content;
	private Date createAt, updateAt;
	
	// 사용자 정보
	private String nickName, userImage; 
}
