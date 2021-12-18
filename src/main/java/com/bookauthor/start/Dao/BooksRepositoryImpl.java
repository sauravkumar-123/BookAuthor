package com.bookauthor.start.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bookauthor.start.Constant.Constant;
import com.bookauthor.start.Exception.BookAuthorException;
import com.bookauthor.start.Model.Books;
import com.bookauthor.start.Request.BooksRequest;
import com.bookauthor.start.Utility.Utility;

@Repository
public class BooksRepositoryImpl implements BooksRepository {

	private static final Logger logger=LoggerFactory.getLogger(BooksRepositoryImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	class BooksRowMapper implements RowMapper<Books>{

		@Override
		public Books mapRow(ResultSet rs, int rowNum) throws SQLException {
			Books books=new Books();
			books.setBookid(rs.getString("bookid"));
			books.setBooktitle(rs.getString("booktitle"));
			books.setBookisbnNo(rs.getString("bookisbnNo"));
			books.setTotalpage(rs.getInt("totalpage"));
			books.setBookrating(rs.getString("bookrating"));
			books.setBookprice(rs.getDouble("bookprice"));
			books.setBookpublicationdate(rs.getTimestamp("bookpublicationdate"));
			return books;
		}
		
	}
	
	@Override
	public int addBooksRecord(BooksRequest booksRequest) {
		int row;	
		 try {
			Timestamp publicationdate=Utility.StringdateToTimestamp(booksRequest.getBookpublicationdate()); 
			row=jdbcTemplate.update(Constant.insertbookrecordQuery, new Object[]{"BK"+booksRequest.getBookid(),booksRequest.getBooktitle(),booksRequest.getBookisbnNo(),booksRequest.getTotalpage(),booksRequest.getBookrating(),booksRequest.getBookprice(),publicationdate});
		 }catch (Exception e) {
			  e.printStackTrace();
		      logger.info("Get Exception While Store Books Record{}->"+e.getMessage());
		      throw new BookAuthorException("Unable To Insert books record");
		}
		 return row;
	}

	@Override
	public List<Books> findAllBooksRecord() {
		List<Books> bookistBooks = null;
		try {
	         bookistBooks=jdbcTemplate.query(Constant.findallbookQuery, new BooksRowMapper()/*new BeanPropertyRowMapper<Books>(Books.class)*/);
		}catch (NullPointerException e) {
		  e.printStackTrace();
		  logger.info("Get Exception While fetch Books Record{}->"+e.getMessage());
		  throw new NullPointerException("Books Record Not Found");
		}catch (Exception e) {
	      e.printStackTrace();
	      logger.info("Get Exception While fetch Books Record{}->"+e.getMessage());
	      throw new BookAuthorException("Unable To get books record");
		}
		return bookistBooks;
	}

	@Override
	public Books findBookRecordByBookid(String bookid) {
		Books bookRecord = null;
		try {
		   bookRecord=jdbcTemplate.queryForObject(Constant.findSinglebookQuery, new BooksRowMapper()/*new BeanPropertyRowMapper<Books>(Books.class)*/,bookid);
		}catch (NullPointerException e) {
		   e.printStackTrace();
		   logger.info("Get Exception While fetch Books Record{}->"+e.getMessage());
		 //  return null;
		   throw new NullPointerException("Book Record Not Found");
		} 
		catch (Exception e) {
		  e.printStackTrace();
		  logger.info("Get Exception While fetch A Book Record{}->"+e.getMessage());
		  //throw new BookAuthorException("Unable To get book record");
			return bookRecord;
		}
		return bookRecord;
	}

	@Override
	public int updateBookRecord(BooksRequest booksRequest,String bookid) {
		int row;
		try {
			Timestamp publicationdate=Utility.StringdateToTimestamp(booksRequest.getBookpublicationdate()); 
			row=jdbcTemplate.update(Constant.updatebookrecordQuery,new Object[] { booksRequest.getBooktitle(),booksRequest.getTotalpage(),booksRequest.getBookrating(),booksRequest.getBookprice(),publicationdate,bookid});
		} catch (Exception e) {
			 e.printStackTrace();
		     logger.info("Get Exception While Update Book Record{}->"+e.getMessage());
		     throw new BookAuthorException("Unable To Upadte Book Record");
		}
	 return row;
	}

	@Override
	public int deleteBookRecordBybookid(String bookid) {
		int row;
		try {
			row=jdbcTemplate.update(Constant.deletebookrecordQuery,new Object[] {bookid});
		} catch (Exception e) {
			e.printStackTrace();
		    logger.info("Get Exception While Delete Book Record{}->"+e.getMessage());
		    throw new BookAuthorException("Unable To Delete Book Record");
		}
	 return row;	
	}
	
			
}
