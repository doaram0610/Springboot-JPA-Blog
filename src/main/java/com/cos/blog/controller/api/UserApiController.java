package com.cos.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController // 데이터만 리턴해주는 컨트롤러
public class UserApiController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("save 호출");

		int result = userService.join(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),result);
	}
	
	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user) {	//json 데이터 받으려면 @RequestBody로 하고 아니면 key=value로 받아야 하는데 x-www-form-urlencoded 로 보내면 그냥 받을 수 있다.
		System.out.println("save 호출");

		userService.update(user);
		
		//서버스에서 트랜잭션이 종료되면서 디비에 반영되니 여기에서 세션값을 강제로 변경해주어야 한다.  
		//세션등록
		Authentication authentication = authenticationManager.authenticate(new  UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}	
	
//	@PostMapping("/api/user/login")
//	public ResponseDto<Integer> login(@RequestBody User user, HttpSession session) {
//		System.out.println("login 호출");
//		
//		User principal = userService.login(user);	//principal:접근주체
//
//		if(principal != null){
//			session.setAttribute("principal", principal);
//		}
//		
//		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
//	}	
}
