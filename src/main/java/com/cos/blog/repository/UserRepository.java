package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cos.blog.model.User;

// DAO
// 자동으로 Bean 등록이 된다.
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	//JPA Naming 쿼리 : where 조건에 넣는 순서대로 이름을 생성한다.
	//SELECT * FROM user WHERE username = ? AND password = ?;
	User findByUsernameAndPassword(String username, String password);
	
	//findByUsernameAndPassword 함수와 동일한 결과를 갖는다. 
//	@Query(value="SELECT * FROM user WHERE username = ? AND password = ?", nativeQuery=true)
//	User login(String username, String password);
}
