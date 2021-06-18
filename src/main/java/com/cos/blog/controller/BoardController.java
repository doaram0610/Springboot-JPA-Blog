package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.blog.model.Board;
import com.cos.blog.service.BoardService;

//화면에 jsp 보여지는 서비스만 Controller 이고 
//데이터를 저장, 삭제 처리하는 것은 RestController를 사용하여 추후 모바일 서비스에 대비한다. 
@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	//글목록
	//Controller 는 viewResolver가 작동한다. 이렇게 된다. webapp/WEB-INF/views/+ index +.jsp
	@GetMapping({"", "/"})
	public String index(Model model, @PageableDefault(size=3, sort="id", direction=Sort.Direction.DESC)Pageable pageable) {
		Page<Board> pageList = boardService.list(pageable);	//Page를 넘겨야지 페이징 정보를 받기 편하다
		
		model.addAttribute("boards", pageList);
		return "index";
	}	
	
	//글상세보기
	@GetMapping("/board/{id}")
	public String findById(@PathVariable int id, Model model) {
		model.addAttribute("board",boardService.detail(id));
		
		return "board/detail";
	}
	
	//글쓰기화면
	//webapp/WEB-INF/views/+ board/saveForm +.jsp
	//로그인 정보가 필요함
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}	
	
	//글수정화면
	//로그인 정보가 필요함
	@GetMapping("/board/{id}/updateForm")
	public String saveForm(@PathVariable int id, Model model) {
		
		model.addAttribute("board",boardService.detail(id));
		return "board/updateForm";
	}	
}
