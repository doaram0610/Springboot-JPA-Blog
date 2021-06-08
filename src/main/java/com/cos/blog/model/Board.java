package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity //Board클래스가 MySql에 테이블 생성
public class Board {

	@Id 
	@GeneratedValue(strategy =  GenerationType.IDENTITY) //시퀀스 자동 생성
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob
	private String content;	//html 코드가 들어간 데이터
	
	@ColumnDefault("0")
	private int count;
	
	@ManyToOne	// Many = Board, One = User  다대일
	@JoinColumn(name = "userId")
	private User user;	//DB 는 오브젝트를 저장할수 없다. FK,자바는 오브젝트를 저장할 수 있다.
	
	@OneToMany(mappedBy="board", fetch=FetchType.LAZY)
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate;
}
