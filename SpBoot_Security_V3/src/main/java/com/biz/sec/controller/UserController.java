package com.biz.sec.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.sec.domain.UserVO;
import com.biz.sec.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(value = "/user")
@Controller
public class UserController {

	private final UserService uService;
	
	@RequestMapping(value = "/login",method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/join",method=RequestMethod.GET)
	public String join(Model model) {
		
		model.addAttribute("userVO", new UserVO());
		
		return "join";
	}
	
	@RequestMapping(value = "/join",method=RequestMethod.POST)
	public String join(UserVO userVO, Model model) {
		
		/*
		 * Optional에 객체를 담는 방법
		 * Optional<class> 객체1 = Optional.of(객체)
		 * -- 객체가 null일경우 exception을 발생
		 * Optional<class> 객체2 = Optional.ofNullable(객체)
		 * -- 객체가 null이어도 무시하고 진행
		 */
		Optional<UserVO> opUserVO = Optional.of(userVO);
		
		uService.insert(opUserVO);
		
		//model.addAttribute("userVO", userVO);
		
		return "redirect:/";
	}
}
