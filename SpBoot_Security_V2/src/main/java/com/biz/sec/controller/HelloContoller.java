package com.biz.sec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloContoller {

	@RequestMapping(value = "/")
	public String hello() {
		return "redirect:/bbs/list";
	}
}
