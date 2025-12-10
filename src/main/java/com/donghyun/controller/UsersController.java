package com.donghyun.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.donghyun.domain.UsersVO;
import com.donghyun.service.UsersService;

import lombok.extern.log4j.Log4j;

/**
 * Description : UsersController<br>
 * Date : 2025. 12. 6.<br>
 * History :<br>
 * - 작성자 : 이동현, 날짜 : 2025. 12. 6.<br>
 *
 * @author 이동현
 * @version 1.0
 */
@Controller
@Log4j
@RequestMapping("/Users/*")
public class UsersController {
	
	@Autowired
	private UsersService service;
	
	@GetMapping("/get")
	public void get(@RequestParam("usersId") Long usersId, Model model) {
		log.info("Users Get---------------------");
		UsersVO users = service.getUsers(usersId);
		
		model.addAttribute("users", users);
	}
}
