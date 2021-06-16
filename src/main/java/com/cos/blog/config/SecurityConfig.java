package com.cos.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


//아래 세개 어노테이션은 세트로 움직이니 항상 세개를 등록하면 된다.
@Configuration
@EnableWebSecurity	//시큐리티 필터가 등록된다. 
@EnableGlobalMethodSecurity(prePostEnabled = true)	//특정주소로 접근하면 권한 및 인증을 미리 체크한다.
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	//일반 글자를 암호화 해준다.
	@Bean //리턴하는 값이 IoC 가 된다. 이걸 스프링이 관리한다. 필요할때마다 쓸수 있다.
	public BCryptPasswordEncoder encodePWD() {		
//		String encPassword = new BCryptPasswordEncoder().encode("1234");
//		System.out.println("password="+ encPassword);
		return new BCryptPasswordEncoder();
	}
	
	//시큐리티가 암호화한 방식이 먼지를 알아야 비교할 수 있다.
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()	//csrf 토큰 비활성화(테스트 시에 걸어두는게 좋다)
		.authorizeRequests()
			.antMatchers("/auth/**", "/css/**", "/image/**", "/js/**")	//이 요청이 들어오면
			.permitAll()	//요청을 허락하고
			.anyRequest()	//아니면(=다른 요청이면)
			.authenticated()	//허용하지않는다. 인증이 필요하다는 뜻
		.and()
			.formLogin()	//위 authenticated 에 걸려서 인증이 필요한 경우에는 
			.loginPage("/auth/loginForm")	//이 로그인 페이지를 보여준다.
			.loginProcessingUrl("/auth/loginProc")	//스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인 해준다.
			.defaultSuccessUrl("/blog")			
			; 
	}
}
