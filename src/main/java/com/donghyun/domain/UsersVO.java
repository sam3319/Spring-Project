package com.donghyun.domain;

import java.util.Date;

import lombok.Data;

/**
 * Description : UsersVO<br>
 * Date : 2025. 12. 5.<br>
 * History :<br>
 * - 작성자 : 이동현, 날짜 : 2025. 12. 5.<br>
 *
 * @author 이동현
 * @version 1.0
 */
@Data
public class UsersVO {
	
	private Long usersId;
	private String nickName, userImage;
	private Date createAt;
}
