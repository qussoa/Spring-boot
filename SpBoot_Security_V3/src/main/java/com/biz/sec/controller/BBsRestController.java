package com.biz.sec.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.biz.sec.domain.BBsVO;
import com.biz.sec.service.BBsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
/*
 * RestController return type에 관계없이 
 * 모든 값을 json 객체형으로 web에 되돌려 준다
 */
@RestController
@RequestMapping(value = "/bbs/api")
public class BBsRestController {

	private final BBsService bService;
	
	@RequestMapping(value = "/json",method=RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:3000")
	public List<BBsVO> getBBsList(){
		List<BBsVO> bbsList = bService.getBBsList();
		return bbsList;
	}
	
	@RequestMapping(value = "/insert",method=RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:3000")
	public String insert(BBsVO bbsVO) {
		log.debug("게시판 데이터 : "+bbsVO.toString());
		bService.insert(bbsVO);
		return "OK";
	}
	
	
}
