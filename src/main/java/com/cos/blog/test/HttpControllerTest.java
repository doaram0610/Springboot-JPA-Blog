package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//사용자가 요청 -> 응답(Html 파일)
//@Controller

//사용자가 요청 -> 응답(Data)
@RestController
public class HttpControllerTest {

	private static final String TAG="HttpControllerTest";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		
		//파라미터 순서 없이 생성할 수 있다. lombok 기능
		Member m = Member.builder().id(23456).username("test1").email("testemail").build();
		
		System.out.println(TAG+"getter : "+m.getId());
		m.setId(120);
		System.out.println(TAG+"getter : "+m.getId());
		
		return "lombok Test 완료  ";
	}
	
	//http://localhost:8080/http/get(select)
	@GetMapping("/http/get")
	public String getTest(Member m) {
		System.out.println(TAG+"getter : "+m.getId());
		
		return "get 요청 : " + m.getId() +", username :"+ m.getUsername();
	}
	
	//http://localhost:8080/http/post(insert)
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) {
		return "post 요청 : " + m.getId() +", username :"+ m.getUsername();
	}
	
	//http://localhost:8080/http/put(update)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청 : "+ m.getId() +", username :"+ m.getUsername();
	}
	
	//http://localhost:8080/http/delete(delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}