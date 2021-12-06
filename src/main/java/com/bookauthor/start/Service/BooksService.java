package com.bookauthor.start.Service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bookauthor.start.Request.BooksRequest;

@Service
public interface BooksService {

	public String addBookdetails(BooksRequest booksRequest);
	public List<Map<String, Object>> getAllBookdetails();
	public List<Map<String, Object>> getBookdetailByid(String bookid);
	public String updateBookdetailByid(BooksRequest booksRequest,String bookid); 
	public String deleteBookdetailByid(String bookid);
}
