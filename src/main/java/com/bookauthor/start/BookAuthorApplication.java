package com.bookauthor.start;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.bookauthor.start.Dao.BooksRepositoryImpl;
import com.bookauthor.start.ServiceImpl.BooksServiceImpl;

import net.bytebuddy.asm.Advice.This;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableScheduling
public class BookAuthorApplication {

	private static final Logger logger=LoggerFactory.getLogger(BookAuthorApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BookAuthorApplication.class, args);
	}
}
