package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity //Reply클래스가 MySql에 테이블 생성
public class Reply {

	@Id 
	@GeneratedValue(strategy =  GenerationType.IDENTITY) //시퀀스 자동 생성
	private int id;
	
	@Column(nullable = false, length = 200)
	private String content;	
	
	@ManyToOne
	@JoinColumn(name = "boardId")
	private Board board;
	
	@ManyToOne	// Reply 와 User 의 관계
	@JoinColumn(name = "userId")
	private User user;
	
	@CreationTimestamp
	private Timestamp createDate;
}
