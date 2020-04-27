package com.biz.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

//JPA를 사용하기 위한 DB 설정
@Entity
@Table(name="tbl_users",schema="spBoot")
public class UserVO {

	/*
	 * Generator값을 auto로 설계하면
	 * 
	 * 오라클 처럼 AUTO INCREMENT 옵션이 없는 DBMS에서 사용
	 * @GeneratedValue(strategy = GenerationType.SEQUENCE)
	 * 
	 * DBMS에 관계없이 사용하는 방법 : Auto로 설정을 하면
	 * TABLE 속성과 같게 작동ㄷ3ㅚㄴ다
	 * @GeneratedValue(strategy = GenerationType.TABLE)
	 * 
	 * AUTO INCREMETN 기능이 있는 DBMS : MySQL, MSSQL, MariaDB
	 * @GeneratedValue(strategy = GenerationType.AUTO)
	 * 
	 * MYSQL 사용할때는 AUTO 옵션보다는 IDENTITY를 사용하자
	 */
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String username;
	private String password;
	private String email;
	private String phone;
	private String address;
	
	
}
