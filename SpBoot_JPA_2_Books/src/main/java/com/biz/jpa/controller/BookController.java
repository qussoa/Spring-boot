package com.biz.jpa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.jpa.domain.BookVO;
import com.biz.jpa.service.BookService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(value = "/book")
@Controller
public class BookController {

	private final BookService bService;


	@RequestMapping(value = "/pageList",method=RequestMethod.GET)
	public String getPageList(Pageable page, Model model) {
		Page<BookVO> bookList = bService.getpageList(page);
		model.addAttribute("bookList",bookList);
		//return bookList;
		return "bookList";
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String hello() {
		return "redirect:/book/list";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String book(Model model) {
		List<BookVO> bookList = bService.selectAll();

		model.addAttribute("bookList", bookList);
		return "layout";
	}

	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert(Model model) {
		model.addAttribute("book", new BookVO());
		return "bookInput";
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(Model model, BookVO bookVO) {
		BookVO vo = bService.save(bookVO);

		return "redirect:/book/list";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(String id, Model model) {
		long bookId = 0;
		try {
			bookId = Long.valueOf(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Optional<BookVO> bookVO = bService.findById(bookId);
		model.addAttribute("book", bookVO.get());
		return "bookInput";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(BookVO bookVO) {
		BookVO vo = bService.save(bookVO);

		return "redirect:/book/list";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(String id) {

		long bookId = 0;
		try {
			bookId = Long.valueOf(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		bService.delete(bookId);
		return "redirect:/book/list";
	}

}