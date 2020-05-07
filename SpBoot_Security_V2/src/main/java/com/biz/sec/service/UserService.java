package com.biz.sec.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.biz.sec.domain.UserVO;
import com.biz.sec.repository.UserDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

	private final UserDao uDao;
	private final PasswordEncoder encPassword; 
	
	
	public void insert(Optional<UserVO> opUserVO) {

		// vo로부터 password를 추출하여 BCrypt 암호화를 한 후
		// 다시 VO에 setting하고 Dao.save()로 전달
		UserVO userVO = opUserVO.get();
		String strPassword = userVO.getPassword();
		
		log.debug("UserVO get pw : " + userVO.getPassword() );
		String strEncPassword =encPassword.encode(strPassword);
		userVO.setPassword(strEncPassword);
		userVO.setEnabled(true);
		userVO.setAccountNonExpired(true);
		userVO.setAccountNonLocked(true);
		userVO.setCredentialsNonExpired(true);
		
		UserVO retUserVO = uDao.save(userVO);
	}
}
