package com.donghyun.service;

import com.donghyun.domain.UsersVO;

/**
 * Description : UsersService<br>
 * Date : 2025. 12. 5.<br>
 * History :<br>
 * - 작성자 : 이동현, 날짜 : 2025. 12. 5.<br>
 *
 * @author 이동현
 * @version 1.0
 */
public interface UsersService {
	// 사용자 정보 가져오기
	public UsersVO getUsers(Long usersId);
}
