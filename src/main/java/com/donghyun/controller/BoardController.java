package com.donghyun.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.donghyun.domain.BoardVO;
import com.donghyun.domain.UsersVO;
import com.donghyun.service.BoardService;

import lombok.extern.log4j.Log4j;

/**
 * Description : BoardController<br>
 * Date : 2025. 12. 9.<br>
 * History :<br>
 * - 작성자 : 이동현, 날짜 : 2025. 12. 9.<br>
 *
 * @author 이동현
 * @version 1.0
 */
@Controller
@Log4j
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	private String uploadPath = "C:/upload/boards/";
	
	// 게시글 작성 페이지 이동
	@GetMapping("/post")
	public String postPage() {
		return "jsp/post";
	}
	
	// 게시글 작성 Post 요청 
	@PostMapping("/post")
	public String insertBoard(@RequestParam("title")String title, @RequestParam("content")String content, @RequestParam("files")List<MultipartFile>files, HttpSession session, RedirectAttributes rttr) {
		log.info("게시글 작성 요청");
		try {
			UsersVO loginUser = (UsersVO)session.getAttribute("loginUser");
			
			log.info("로그인 정보: " + loginUser);
			// 현재 세션에 로그인 정보가 있는지 확인
			if(loginUser == null) {
				rttr.addFlashAttribute("message", "로그인이 필요합니다.");
				return "redirect:/";
			}
			 
			// Board 객체 생성
			BoardVO board = new BoardVO();
			board.setUsersId(loginUser.getUsersId());
			board.setTitle(title);
			board.setContent(content);
		
			// 업로드 폴더 생성
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}
			
			// 파일 이름을 저장할 변수
			List<String> fileName = new ArrayList<String>();
			
			// 파일 객체 생성
			for(MultipartFile file:files) {
				String org_FileName = file.getOriginalFilename();
				
				String extension = org_FileName.substring(org_FileName.lastIndexOf("."));
				String uuid = UUID.randomUUID().toString();
				String savedFileName = uuid + extension;
				
				File targetFile = new File(uploadPath + savedFileName);				
				file.transferTo(targetFile);
				
				fileName.add(savedFileName);
			}
			
			// boardVO에 이미지, 게시글 정보 저장 후 등록
			board.setImages(fileName);
			
			service.insertBoard(board);
			
			rttr.addFlashAttribute("message", "게시글이 등록되었습니다");
			
			return "redirect:/";
		}catch(Exception e) {
			rttr.addFlashAttribute("message", "게시글 등록에 실패했습니다." + e.getMessage());
			return "redirect:/board/post";
		}
	}
	
	@GetMapping("/update")
	// 게시글 수정 페이지 이동
	public String updatePage(@RequestParam("bno")Long bno, HttpSession session, Model model) {
		log.info("게시글 수정 페이지 이동");
		
		UsersVO loginUser = (UsersVO)session.getAttribute("loginUser");
		if(loginUser == null) {
			return "redirect:/";
		}
		
		BoardVO board = service.getBoard(bno);
		
		if(!board.getUsersId().equals(loginUser.getUsersId())) {
			return "redirecet:/";
		}
		
		model.addAttribute("board", board);
		return "jsp/update";
	}
	
	@PostMapping("/update")
	// 게시글 수정
	public String update(@RequestParam("bno")Long bno, @RequestParam("title")String title, @RequestParam("content")String content) {
		log.info("게시글 수정");
		
		BoardVO board = service.getBoard(bno);
		
		board.setTitle(title);
		board.setContent(content);
		
		service.modifyBoard(board);
		
		return "redirect:/";
	}
	
	@PostMapping("/delete")
	// 게시글 삭제
	public String delete(@RequestParam("bno")Long bno) {
		log.info("게시글 삭제");
		
		service.removeBoard(bno);
		
		return "redirect:/";
	}
	
	@GetMapping("/profile")
	// 프로필 페이지 이동
	public String profilePage(HttpSession session, Model model) {
		UsersVO loginUser = (UsersVO)session.getAttribute("loginUser");
		
		model.addAttribute("loginUser", loginUser);
		
		int postCount = service.getUserPostCount(loginUser.getUsersId());
	    model.addAttribute("postCount", postCount);

	    
	    List<Map<String,Object>> thumbnails = service.getUserPostThumbnails(loginUser.getUsersId());
	    model.addAttribute("thumbnails", thumbnails);
		return "jsp/profile";
	}
}
