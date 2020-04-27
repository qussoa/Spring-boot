package com.biz.jpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.biz.jpa.domain.BookVO;
import com.biz.jpa.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookService {

	private final BookRepository bookDao;
	
	/*
	 * spring-date 패키지의
	 * pageable, page, pageRequest 클래스를 사용하여
	 * pagination을 구현
	 */
	public Page<BookVO> getpageList(Pageable page) {
		int pageNum = 0;
		if(page.getPageNumber() == 0) pageNum = 0;
		else pageNum = page.getPageNumber() -1;
		page = PageRequest.of(pageNum, 10);
		return bookDao.findAll(page);
	}
	
	public List<BookVO> selectAll() {
		// TODO selectAll
		List<BookVO> bookList = bookDao.findAll(); 
		return bookList;
	}

	public BookVO save(BookVO bookVO) {
		// TODO Auto-generated method stub
		BookVO vo = bookDao.save(bookVO);
		return vo;
	}

	public Optional<BookVO> findById(long bookId) {
		// TODO findById
		Optional<BookVO> bookVO = bookDao.findById(bookId);
		
		return bookVO;
	}

	public void delete(long bookId) {
		// TODO Auto-generated method stub
		bookDao.deleteById(bookId);
	}

	
}
