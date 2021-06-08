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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity //User클래스가 MySql에 테이블 생성
public class User {
	
	@Id 
	@GeneratedValue(strategy =  GenerationType.IDENTITY) //시퀀스 자동 생성
	private int id;
	
	@Column(nullable = false, length = 30)
	private String username;
	
	@Column(nullable = false, length = 100)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	@Enumerated(EnumType.STRING)
	//@ColumnDefault("'user'")
	private RoleType role; //Enum 쓰는게 좋다 //admin, user, manager
	
	@CreationTimestamp	//시간 자동 입력
	private Timestamp createDate;
	

}
