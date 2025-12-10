package com.donghyun.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.donghyun.controller.UsersControllerTests;
import lombok.extern.log4j.Log4j;

/**
 * Description : UsersControllerTests<br>
 * Date : 2025. 12. 5.<br>
 * History :<br>
 * - 작성자 : 이동현, 날짜 : 2025. 12. 5.<br>
 *
 * @author 이동현
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration //Test for Controller 
@ContextConfiguration({
  "file:src/main/webapp/WEB-INF/spring/root-context.xml",
  "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
public class UsersControllerTests {
	
	@Autowired
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	// 사용자 정보 출력 테스트 코드
	@Test
	public void testGetUser() throws Exception {
		log.info(mockMvc.perform(get("/Users/get")
				.param("usersId", "1"))
				.andReturn());
	}
}
