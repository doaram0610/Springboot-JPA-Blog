package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;

import lombok.Getter;

//스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면  UserDetails 타입의 오브젝트를 
//스프링 시큐리티의 고유한 세션저장소에 저장한다.
@Getter
public class PrincipalDetail implements UserDetails{

	private User user;	//콤포지션, 내가 사용하는 사용자객체정보

	public PrincipalDetail(User user) {
		this.user = user;
	}
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	//계정이 만료되지 않았는지 리턴(true:만료안됨)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	//계정이 잠겨 있는지 리턴(true:잠겨있지 않음)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	//비밀번호가 만료되지 않앗는지 있는지 리턴(true:만료안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//계정이 활성화(사용가능)인지 리턴(true:활성화)
	@Override
	public boolean isEnabled() {
		return true;
	}

	//계정이 갖고 있는 권한목록을 리턴
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collectors = new ArrayList<>();
//		collectors.add(new GrantedAuthority() {
//			
//			@Override
//			public String getAuthority() {
//				return "ROLE_"+user.getRole();	//ROLE_USER 고정적인 형식이다 앞에 꼭!! 붙여야 한다.
//			}
//		});	
		
		//람다식
		collectors.add(()->{return "ROLE_"+user.getRole();});//갖고 있는 함수가 1개 일 경우 이렇게 호출할 수 있다. 여기서는 권한범위가 role필드 한개이기때문
		
		return collectors;
	}
	
	
}
