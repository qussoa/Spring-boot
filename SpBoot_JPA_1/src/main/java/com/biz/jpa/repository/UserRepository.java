package com.biz.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biz.jpa.domain.UserVO;

/*
 * JPA에서 DB CRUD를 쉽게 구현하기 위해
 * JPA Repository를 상속받고 Generic에 <VO,id의 type> 지정
 */
public interface UserRepository extends JpaRepository<UserVO, Long>{

}
