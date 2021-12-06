package com.bookauthor.start.Dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bookauthor.start.Model.Books;
import com.bookauthor.start.Request.BooksRequest;

@Repository
public interface BooksRepository {

	public int addBooksRecord(BooksRequest booksRequest);
	public List<Books> findAllBooksRecord();
	public Books findBookRecordByBookid(String bookid);
	public int updateBookRecord(BooksRequest booksRequest,String bookid);
	public int deleteBookRecordBybookid(String bookid);
}
