package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {

	//http://localhost:8000/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		
		System.out.println("temp home");
		
		//src/main/resources/static + home.html
		return "/home.html";
	}
	
	//static 폴더는 jsp 는 인식 못한다. 정적인 파일만 인식한다.
	//http://localhost:8000/blog/temp/img
	@GetMapping("/temp/img")
	public String tempImg() {
		
		System.out.println("temp png");
		
		//src/main/resources/static +a.png
		return "/a.png";
	}
	
	
	//static 폴더는 jsp 는 인식 못한다. 정적인 파일만 인식한다.
	//http://localhost:8000/blog/temp/img
	@GetMapping("/temp/jsp")
	public String tempJsp() {
		
		System.out.println("temp jsp");
		
		///WEB-INF/views/ + 파일명 +.jsp
		return "test";
	}
	
}
