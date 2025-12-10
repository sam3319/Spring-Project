package com.donghyun.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.donghyun.domain.UsersVO;
import com.donghyun.service.UsersService;

/**
 * Description : HomeController<br>
 * Date : 2025. 12. 5.<br>
 * History :<br>
 * - 작성자 : 이동현, 날짜 : 2025. 12. 5.<br>
 *
 * @author 이동현
 * @version 1.0
 */
@Controller
@RequestMapping("/")
public class HomeController {
	@Autowired
	private UsersService usersService;
	// 홈 화면
	@GetMapping
	public String home(HttpSession session, Model model) {
		// 자동 로그인 (개인 SNS 이기 때문에 실행 시 즉시 로그인)
		UsersVO loginUser = (UsersVO)session.getAttribute("loginUser");
		if(loginUser == null) {
			loginUser = usersService.getUsers((long) 1);
			session.setAttribute("loginUser", loginUser);
		}
		
		model.addAttribute("loginUser", loginUser);
		
		
		return "jsp/index";
	}
	
}
