package com.biz.sec;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.biz.sec.domain.BBsVO;
import com.biz.sec.repository.BBsDao;

@SpringBootApplication
public class SpBootSecurityV1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpBootSecurityV1Application.class, args);
	}
	
	/*
	 * Spring boot project 가 시작될때 실행할 일들을 작성하는 방법
	 * 
	 * 이 method는 @Bean Annotation을 제거하면
	 * 자동 실행을 하지 않는다
	 */
	

	
	//@Bean
	public CommandLineRunner initBBsData(BBsDao bDao) {
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				// TODO Auto-generated method stub
				for (int i = 0; i < 10; i++) {
					LocalDate localDate = LocalDate.now();
					LocalDateTime lt = LocalDateTime.now();
					String title = String.format("%s", lt.toString());
					String date = localDate.toString();

					BBsVO bbsVO = BBsVO.builder()
							.bbsTitle(title)
							.bbsAuth("LEEJEONGYEON")
							.bbsDate(date).build();
					bDao.save(bbsVO);
				}

			}
		};
	}

}
