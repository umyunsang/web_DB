package com.web.p5;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class diary {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //*Auto_increment 사용이 필요 어노테이션
	public Integer no;
	public String title;
	public String content;
	@CreationTimestamp //* 이런 어노테이션이 있었다 기억
	public LocalDateTime wdate;
}
