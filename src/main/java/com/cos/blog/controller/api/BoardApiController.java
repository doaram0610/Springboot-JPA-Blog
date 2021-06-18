package com.cos.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.service.BoardService;

@RestController // 데이터만 리턴해주는 컨트롤러
public class BoardApiController {

	@Autowired
	private BoardService boardService;
	
	//글신규등록
	//@AuthenticationPrincipal PrincipalDetail principal  : 스프링시큐리티의 세션값 
	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
		
		System.out.println("Call @RestController : /api/board");
		
		boardService.save(board, principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}

	//http://127.0.0.1?index=1&page=2 이 요청시에는 @RequestParam 로 파라미터값을 받고
	//http://127.0.0.1/index/1  이 요청시에는 @PathVariable 로 파라미터 값을 받는다. URL에서 구분자에 들어오는 값을 받을때(모바일에서 많이 사용)
	//글삭제
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id) {
		
		System.out.println("Call @RestController : deleteById(/api/board/+id) : "+id);
		
		boardService.deleteById(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	//글수정
	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board) {
		
		System.out.println("Call @RestController : update(/api/board/+id) : "+id);
		
		boardService.update(id, board);		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}	
	
}
