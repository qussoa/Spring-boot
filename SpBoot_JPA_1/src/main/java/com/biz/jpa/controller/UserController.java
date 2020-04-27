package com.biz.jpa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.jpa.domain.UserVO;
import com.biz.jpa.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/user")
@Controller
public class UserController {

	private final UserService uService;
	
	
	@RequestMapping(value = "/list",method=RequestMethod.GET)
	public String userList(Model model) {
		List<UserVO> userList = uService.selectAll();
		
		model.addAttribute("userList",userList);
		
		
		return "layout";
	}
	
	@RequestMapping(value = "/insert", method=RequestMethod.GET)
	public String user(Model model) {
		model.addAttribute("user",new UserVO());
		return "userForm";
	}
	
	//@ResponseBody
	@RequestMapping(value = "/insert", method=RequestMethod.POST)
	public String user(Model model, UserVO userVO) {
		log.debug(userVO.toString());
		
		UserVO vo =uService.save(userVO);
		
		return "redirect:/user/list";
		//return vo;
	}

	
	@RequestMapping(value = "/update",method=RequestMethod.GET)
	public String update(String id, Model model) {
		
			long userId = 0;
		try {
			userId = Long.valueOf(id);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Optional<UserVO> userVO = uService.findById(userId);
		model.addAttribute("user",userVO.get());
		return "userForm";
	}
	
	@RequestMapping(value = "/update",method=RequestMethod.POST)
	public String update(UserVO userVO) {
		
		UserVO vo = uService.save(userVO);
		
		
		return "redirect:/user/list";
	}
	@RequestMapping(value = "/delete",method=RequestMethod.GET)
	public String delete(String id) {
		
		long userId = 0;
		try {
			userId = Long.valueOf(id);	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		uService.delete(userId);
		
		return "redirect:/user/list";
		
	}
}
