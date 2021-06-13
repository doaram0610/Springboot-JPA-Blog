package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

	//webapp/WEB-INF/views/index.jsp
	@GetMapping("/")
	public String index() {
		return "index";
	}
}
