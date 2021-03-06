package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@DynamicInsert	//insert 할때 null인 필드를 생략해준다.
@Builder	//생성자를 호출할때 보내야 하는 파라미터를 순서 상관없이 이름으로 지정할 수 있도록 한다. 예)val user = User(username = "zorba", password = "1111")
@NoArgsConstructor	//파라미터 없는 생성자 함수 생성
@AllArgsConstructor	//모든 파라미터 있는 생성자 함수 생성
@Data	//getter + setter 생성
@Entity //User클래스가 DB 에 테이블 생성
public class User {
	
	@Id 
	@GeneratedValue(strategy =  GenerationType.IDENTITY) //시퀀스 자동 생성
	private int id;
	
	@Column(nullable = false, length = 100, unique = true) //중복허용 안함(unique = true)
	private String username;
	
	@Column(nullable = false, length = 100)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	private String oauth;	//kakao, google
	
	@Enumerated(EnumType.STRING)
	//@ColumnDefault("'user'")
	private RoleType role; //Enum 쓰는게 좋다 //admin, user, manager
	
	@CreationTimestamp	//시간 자동 입력
	private Timestamp createDate;
	

}
