package com.donghyun.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.donghyun.domain.UsersVO;
import com.donghyun.mapper.UsersMapperTests;
import lombok.extern.log4j.Log4j;

/**
 * Description : UsersMapperTests<br>
 * Date : 2025. 12. 5.<br>
 * History :<br>
 * - 작성자 : 이동현, 날짜 : 2025. 12. 5.<br>
 *
 * @author 이동현
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class UsersMapperTests {
	@Autowired
	private UsersMapper mapper;
	
	@Test
	public void testGetUsers() {
		UsersVO users = mapper.getUsers(1L);
		log.info(users);
	}
	
	
}
