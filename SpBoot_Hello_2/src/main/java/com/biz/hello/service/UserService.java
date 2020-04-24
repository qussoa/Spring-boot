package com.biz.hello.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.biz.hello.domain.UserVO;

@Service
public class UserService {

	public List<UserVO> getUserList() {
		List<UserVO> userList = new ArrayList<>();

		userList.add(
				UserVO.builder()
				.username("홍길동")
				.password("1234")
				.email("qussoa@naver.com")
				.address("서울")
				.phone("010-0000-0000")
				.build()
				);
		
		userList.add(
				UserVO.builder()
				.username("이몽룡")
				.password("5678")
				.email("qussoa@naver.com")
				.address("익산")
				.phone("010-1234-1234")
				.build()
				);
		
		userList.add(
				UserVO.builder()
				.username("성춘향")
				.password("9012")
				.address("남원")
				.email("qussoa@naver.com")
				.phone("010-1234-1234")
				.build()
				);

		return userList;

	}

}
