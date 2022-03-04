package com.bookauthor.start.RepositoryTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.bookauthor.start.Dao.BooksReposEntityManager;
import com.bookauthor.start.Model.Books;
import com.bookauthor.start.Request.BooksRequest;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class BookAuthorRepositoryTest {

	private static final Logger logger=LoggerFactory.getLogger(BookAuthorRepositoryTest.class);
	
	@Autowired
	private BooksReposEntityManager booksReposEntityManager;
	
	@BeforeEach
	public void BeforeMethod() {
		logger.info("Before Method{}-->"+"Test Started");
	}
	
	@Test
	void test1() {
		Books books=booksReposEntityManager.findBookRecordByBookid(1L);
		logger.info("Result:{}---->"+books);
		assertEquals("BK00356", books.getBookid());
	}
	
	@Test
	@DirtiesContext
	@Timeout(value = 1, unit = TimeUnit.SECONDS)
	void test2() {
		String status=booksReposEntityManager.deleteBookRecordBybookid(1L);
		assertEquals("success", status);
	}
	
	@Test
	void test3() {
		Books books=booksReposEntityManager.findBookRecordByBookid(1L);
		logger.info("Result:{}---->"+books);
		assertEquals("BK584100", books.getBookid());
		
		BooksRequest booksRequest=new BooksRequest();
		booksRequest.setBookid("BK5600210");
		booksRequest.setBookisbnNo("ISBN002541");
		booksRequest.setBookprice(855.95);
		booksRequest.setBookpublicationdate("25/10/2020 12:50:00");
		booksRequest.setBookrating("5star");
		booksRequest.setBooktitle("DiscriteMath");
		booksRequest.setTotalpage(585);
		assertEquals("success", booksReposEntityManager.addBooksRecord(booksRequest));
		
//		Books booksRecord=booksReposEntityManager.findBookRecordByBookid(2L);
//		logger.info("Result:{}---->"+booksRecord);
//		assertEquals("BK5600210", booksRecord.getBookid());
	}
	@AfterEach
	public void AfterMethod() {
		logger.info("After Method{}-->"+"Test End");
	}
}
