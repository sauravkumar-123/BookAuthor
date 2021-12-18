package com.bookauthor.start.Dao;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.bookauthor.start.Exception.BookAuthorException;
import com.bookauthor.start.Model.Books;
import com.bookauthor.start.Request.BooksRequest;
import com.bookauthor.start.Utility.Utility;

@Repository
@Transactional
public class BooksReposEntityManagerImpl implements BooksReposEntityManager {

	private static final Logger logger=LoggerFactory.getLogger(BooksReposEntityManagerImpl.class);
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public String addBooksRecord(BooksRequest booksRequest) {
		String status="";	
		 try {
			 if(null!=booksRequest.getBookid() && !booksRequest.getBookid().isEmpty() &&
				null!=booksRequest.getBookisbnNo() && !booksRequest.getBookisbnNo().isEmpty() &&
				null!=booksRequest.getBookpublicationdate() && !booksRequest.getBookpublicationdate().isEmpty() &&
				null!=booksRequest.getBookrating() && !booksRequest.getBookrating().isEmpty() &&
			    null!=booksRequest.getBooktitle() && !booksRequest.getBooktitle().isEmpty() &&
			    0!=booksRequest.getTotalpage()
				) { 
				 Timestamp publicationdate=Utility.StringdateToTimestamp(booksRequest.getBookpublicationdate());
				 Books books=new Books();
				 books.setBookid(booksRequest.getBookid());
				 books.setBookisbnNo(booksRequest.getBookisbnNo());
				 books.setBookprice(booksRequest.getBookprice());
				 books.setBookpublicationdate(publicationdate);
				 books.setBookrating(booksRequest.getBookrating());
				 books.setBooktitle(booksRequest.getBooktitle());
				 books.setTotalpage(booksRequest.getTotalpage());
				 entityManager.persist(books);
				 status="success";
			 }else {
				 entityManager.merge(booksRequest);
				 throw new BookAuthorException("Some Invalid Parameter Passing", HttpStatus.BAD_REQUEST);
				// entityManager.merge(booksRequest);
			} 
		 }catch (Exception e) {
			  e.printStackTrace();
		      logger.info("Get Exception While Store Books Record{}->"+e.getMessage());
		      throw new BookAuthorException("Unable To Insert books record");
		}finally {
			entityManager.close();
			entityManager.flush();
		}
		 return status;
	}

	@Override
	public List<Books> findAllBooksRecord() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Books findBookRecordByBookid(Long bookid) {
		Books bookRecord = null;
		try {
			bookRecord=entityManager.find(Books.class, bookid);
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.info("Get Exception While fetch Books Record{}->"+e.getMessage());
			//  return null;
			throw new NullPointerException("Book Record Not Found");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Get Exception While fetch A Book Record{}->"+e.getMessage());
			//throw new BookAuthorException("Unable To get book record");
			return bookRecord;
		}finally {
			entityManager.close();
			entityManager.flush();
		}
		return bookRecord;
	}

	@Override
	public int updateBookRecord(BooksRequest booksRequest, String bookid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String deleteBookRecordBybookid(Long bookid) {
		String status="";
		Books bookRecord = null;
		try {
			bookRecord=entityManager.find(Books.class, bookid);
			logger.info("Book Record :"+bookRecord);
			if(null!=bookRecord) {
				entityManager.remove(bookRecord);
				status="success";
			}else {
				throw new NullPointerException("Book Record Not Found With Id: "+bookid);
			}
		} catch (Exception e) {
			e.printStackTrace();
		    logger.info("Get Exception While Delete Book Record{}->"+e.getMessage());
		    throw new BookAuthorException("Unable To Delete Book Record");
		}finally {
			entityManager.close();
			entityManager.flush();
		}
	 return status;
	}

}
