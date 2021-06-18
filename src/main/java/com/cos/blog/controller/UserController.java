package com.cos.blog.controller;


import org.springframework.boot.autoconfigure.web.reactive.HttpHandlerAutoConfiguration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.cos.blog.model.OAuthToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


//인증이 안된 사용자들이 출입할 수있는 경로를 /auth/** 허용
//그냥 주소가 / 이면 index.jsp 허용
//static이하에 있는 /js/**, /css/**, /image/**

@Controller
public class UserController {
	
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		
		return "user/joinForm";
	}
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		
		return "user/loginForm";
	}	
	
	@GetMapping("/user/updateForm")
	public String updateForm() {
		
		return "user/updateForm";
	}	
	
	//카카오 로그인 연동
	@GetMapping("/auth/kakao/callback")
	public @ResponseBody String kakaoCallback (String code) throws JsonMappingException {	//Data를 리턴해주는 컨트롤러 함수
		
		//POST방식으로 key=value 타입의 데이터를 카카오로 요청해야 한다.
		//예전에 HTTPsURLConnection post 방식을 요청 했었다.		
		
		//HttpHeader 오브젝트 생성
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		
		//HttpHeader 와 HttpBody 를 하나의 오브잭트에 담기. 나중에 값을 변수처리한다
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type","authorization_code");
		params.add("client_id","9fb8744cbc91ff1c85fd1641698b3d84");
		params.add("redirect_uri","http://localhost:8000/blog/auth/kakao/callback");
		params.add("code",code);
		
		//Http 요청 - Post방식으로 그리고 response 변수의 응답 받음
		HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest = new HttpEntity<>(params, headers);
		ResponseEntity<String> response = rt.exchange(
			"https://kauth.kakao.com/oauth/token",
			HttpMethod.POST,
			kakaoTokenRequest,
			String.class
		);
		
		//Gson, Json Simple, ObjectMapper(내장)
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oAuthToken = null;
		try {
			oAuthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println("카카오 엑세스 토큰 : "+oAuthToken.getAccess_token());
		
		return "카카오인증 완료 : "+response.getBody();
	}	
}
