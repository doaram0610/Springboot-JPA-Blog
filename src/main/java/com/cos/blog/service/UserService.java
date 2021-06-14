package com.cos.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;


//스프링이 컴포넌트 스캔을 통해서 Bean 에 등록 해준다. IoC를 해준다
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public int join(User user) {
		try {
			userRepository.save(user);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("UserService"+e.getMessage());			
		}
		return -1;
	}
	
	@Transactional(readOnly = true)	//select할때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성) :종료할때까지 같은데이터를 리턴한다. 
	public User login(User user) {
		
		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}	
}
