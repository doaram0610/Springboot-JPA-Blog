package com.cos.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
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
	private BCryptPasswordEncoder encoder;	//해당 @Bean 에서 리턴하는 값을 @Autowired 할 수 있다
	
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
	
//	@Transactional(readOnly = true)	//select할때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성) :종료할때까지 같은데이터를 리턴한다. 
//	public User login(User user) {
//		
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}	
}
