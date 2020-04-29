package com.biz.sec.domain;

import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "tbl_users")
public class UserVO implements UserDetails{

	private static final long serialVersionUID = 1L;

	/*
	 * JPA의 Entity를 선언할때 
	 * id 칼름(필드변수)는 반드시 class type으로 선언하자
	 * 
	 * 그렇지 않으면 JPA의 자동 method가 작동되지 않는다
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id",columnDefinition = "BIGINT")
	private Long id;
	
	@Column(name="username", columnDefinition = "VARCHAR(64)", unique = true,nullable = false,length = 64)
	private String username;
	
	private String password;
	
	private boolean enabled;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	
	@Transient
	private Collection<? extends GrantedAuthority> authorities;
	
	private String email;
	private String phone;
	private String address;
	
	/*
	 * JPA에서 1:N 관계를 설정하는 부분
	 *  fetch = FetchType.LAZY
	 *  두 테이블을 Join했을때
	 *  master table의 데이터를 select한 후
	 *  바로 slave table을 Select 하지 않고
	 *  slave table에 데이터가 필요한 시점에
	 *  select를 수행하도록 하는 지연 옵션
	 */
	@OneToMany(mappedBy = "userVO", 
			cascade=  {CascadeType.ALL},
			fetch = FetchType.LAZY)
	private Set<UserRole> userRoles;	
}
