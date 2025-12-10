package com.donghyun.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donghyun.domain.UsersVO;
import com.donghyun.mapper.UsersMapper;

/**
 * Description : UsersServiceImpl<br>
 * Date : 2025. 12. 5.<br>
 * History :<br>
 * - 작성자 : 이동현, 날짜 : 2025. 12. 5.<br>
 *
 * @author 이동현
 * @version 1.0
 */
@Service
public class UsersServiceImpl implements UsersService{
	@Autowired
	private UsersMapper mapper;
	
	@Override
	public UsersVO getUsers(Long usersId) {
		// 사용자 정보 가져오기
		return mapper.getUsers(usersId);
	}
}
