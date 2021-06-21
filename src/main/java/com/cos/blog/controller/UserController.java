package com.cos.blog.controller;


import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.cos.blog.model.KakaoProfile;
import com.cos.blog.model.OAuthToken;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


//인증이 안된 사용자들이 출입할 수있는 경로를 /auth/** 허용
//그냥 주소가 / 이면 index.jsp 허용
//static이하에 있는 /js/**, /css/**, /image/**

@Controller
public class UserController {
	
	@Value("${cos.key}")
	private String cosKey;
	
	@Autowired
	UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
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
	
	//카카오 로그인 연동 :  인가 코드 받기 -> 토큰 받기 -> 토큰을 이용해 사용자정보 추출
	@GetMapping("/auth/kakao/callback")
	public String kakaoCallback (String code) throws JsonMappingException {	//Data를 리턴해주는 컨트롤러 함수
		
		//POST방식으로 key=value 타입의 데이터를 카카오로 요청해야 한다.
		//예전에는 HTTPsURLConnection post 방식을 요청 했었다.		
		
		//카카오 REST API 호출 : 토큰 받기
		RestTemplate rt = new RestTemplate();
		
		//HttpHeader 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		
		//HttpBody 오브젝트 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type","authorization_code");
		params.add("client_id","9fb8744cbc91ff1c85fd1641698b3d84");
		params.add("redirect_uri","http://localhost:8000/blog/auth/kakao/callback");
		params.add("code",code);
		
		//HttpHeader 와 HttpBody 를 하나의 오브잭트에 담기. 나중에 값을 변수처리한다
		HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest = new HttpEntity<>(params, headers);
		
		//Http 요청 - Post방식으로 그리고 response 변수의 응답 받음
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
		
		
		//카카오 REST API 호출 : 사용자 정보 받기
		RestTemplate rt2 = new RestTemplate();
		
		//HttpHeader 오브젝트 생성
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer "+ oAuthToken.getAccess_token());
		headers2.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		
		//HttpHeader 와 HttpBdoy 를 하나의 오브젝트에 담기 : 여기서는 param 안넘김
		HttpEntity<MultiValueMap<String,String>> kakaoProfileRequest2 = new HttpEntity<>(headers2);
		
		//Http 요청 - POST방식으로 - 그리고 response 변수의 응답을 받음
		ResponseEntity<String> response2 = rt2.exchange(
			"https://kapi.kakao.com/v2/user/me",
			HttpMethod.POST,
			kakaoProfileRequest2,
			String.class
		);		
		System.out.println("카카오 프로파일 정보 : "+response2.getBody());
		
		//Gson, Json Simple, ObjectMapper(내장)
		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile = null;
		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println("카카오 id(일련번호): "+kakaoProfile.getId());
		System.out.println("카카오 email: "+kakaoProfile.getKakao_account().getEmail());		
		
		
		//User오브젝트 : username, password, email
		System.out.println("블로그서버 유저네임: "+kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId());
		System.out.println("블로그서버 이메일: "+kakaoProfile.getKakao_account().getEmail());		
		//UUID란 - 중복되지 않는 특정값을 만들어낸다. 그래서 매번 암호가 다르니 카카오계정은 지정한 암호 한개로 회원가입 해준다.
		System.out.println("블로그서버 패스워드: "+cosKey);
		
		User kakaoUser = User.builder()
				.username(kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId())
				.password(cosKey)
				.email(kakaoProfile.getKakao_account().getEmail())
				.oauth("kakao")
				.build();
		
		//이미 가입된 회원인지 체크해서 가입시키기
		User originUser = userService.find(kakaoUser.getUsername());
		System.out.println("블로그서버 originUser : "+originUser);
		if(originUser.getUsername() == null) {
			System.out.println("기존 회원이 아니니 신규로 회원가입을 진행합니다.");
			userService.join(kakaoUser);
		}

		//자동 로그인 처리
		Authentication authentication = authenticationManager.authenticate(new  UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), cosKey));
		SecurityContextHolder.getContext().setAuthentication(authentication);
				
		
		return "redirect:/";
	}	
}
