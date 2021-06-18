package com.cos.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;


//스프링이 컴포넌트 스캔을 통해서 Bean 에 등록 해준다. IoC를 해준다
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;	//암호값을 해쉬화해서 암호처리한다. 해당 @Bean 에서 리턴하는 값을 @Autowired 할 수 있다

	
	@Transactional
	public int join(User user) {
		try {
			//암호를 해쉬화 해준다.
			String rawPassword = user.getPassword();
			String encPassword = encoder.encode(rawPassword);					 
			user.setPassword(encPassword);
			
			user.setRole(RoleType.USER);
			userRepository.save(user);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("UserService"+e.getMessage());			
		}
		return -1;
	}
	
	@Transactional
	public void update(User user) {
		//수정시에는 영속성 컨택스트 User오브젝트를 영속화시키고, 영속화된 User오브젝트를 수정하여 처리
		//select를 해서 User오브젝트를 DB 로 부터 가져오는 이유는 영속화를 하기 위해서!!!
		//영속화된 오브젝트를 변경하면 자동으로 DB에서 udate 를 날려준다.
		
		User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원 찾기 실패");
		});
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		persistance.setPassword(encPassword);
		persistance.setEmail(user.getEmail());
		
		//회원수정 함수 종료시 = 서비스 종료시 = 트랜잭션 종료 = commit 이 자동 수행
		//영속화된 persistance 객체가 변화가 감지되면 더티체킹되어 변화된 것을 update 한다.		
	}
	
//	@Transactional(readOnly = true)	//select할때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성) :종료할때까지 같은데이터를 리턴한다. 
//	public User login(User user) {
//		
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}	
}
