package com.cos.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;

@ControllerAdvice	//패키지 어디에서든지 호출할 수 있게 해준다.
@RestController
public class GlobalExceptionHandler {
	
	//모든 Exception 발생 시 아래의 함수가 호출되어 값을 리턴시킨다.
	@ExceptionHandler(value=Exception.class)
	public ResponseDto<String> handleArgumentExeption(Exception e) {
		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
	}
}
