package com.bookauthor.start.ServiceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bookauthor.start.Dao.BooksRepositoryImpl;
import com.bookauthor.start.Exception.BookAuthorException;
import com.bookauthor.start.Model.Books;
import com.bookauthor.start.Request.BooksRequest;
import com.bookauthor.start.Service.BooksService;

@Service
public class BooksServiceImpl implements BooksService{

	private static final Logger logger=LoggerFactory.getLogger(BooksServiceImpl.class);
	
	@Autowired
	private BooksRepositoryImpl booksRepositoryImpl;
	
	@Override
	public String addBookdetails(BooksRequest booksRequest) {
		int noOfrecord;
		String status="";
		Books book=booksRepositoryImpl.findBookRecordByBookid(booksRequest.getBookid());
		if(null!=book) {
			throw new BookAuthorException("Book Record Already Present",HttpStatus.EXPECTATION_FAILED);
		}else {
		 noOfrecord=booksRepositoryImpl.addBooksRecord(booksRequest);
		 if(noOfrecord>0)
			 status="success";
		}
	 return status;	
	}

	@Override
	public List<Map<String, Object>> getAllBookdetails() {
	List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();	
	List<Books> bookslist=booksRepositoryImpl.findAllBooksRecord();
	if(null!=bookslist && !bookslist.isEmpty()) {
		Collections.sort(bookslist, (b1,b2)->b1.getBookisbnNo().compareToIgnoreCase(b2.getBookisbnNo()));
		bookslist.parallelStream().forEach(i->{
	    Map<String, Object> map=new HashMap<String,Object>();
	    map.put("Bookid", i.getBookid());
	    map.put("BookTitle", i.getBooktitle());
	    map.put("BookISBNno", i.getBookisbnNo());
	    map.put("TotalPage", i.getTotalpage());
	    map.put("BookRating", i.getBookrating());
	    map.put("BookPrice", i.getBookprice());
	    map.put("BookPublicationDate", i.getBookpublicationdate());
	    list.add(map);
		});
	}else {
		throw new NullPointerException("Books Record Not Found");
	}
	return list;
	}

	@Override
	public List<Map<String, Object>> getBookdetailByid(String bookid) {
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();	
		Books bookrecord=booksRepositoryImpl.findBookRecordByBookid(bookid);
		if(null!=bookrecord) {
		    Map<String, Object> map=new HashMap<String,Object>();
		    map.put("Bookid", bookrecord.getBookid());
		    map.put("BookTitle", bookrecord.getBooktitle());
		    map.put("BookISBNno", bookrecord.getBookisbnNo());
		    map.put("TotalPage", bookrecord.getTotalpage());
		    map.put("BookRating", bookrecord.getBookrating());
		    map.put("BookPrice", bookrecord.getBookprice());
		    map.put("BookPublicationDate", bookrecord.getBookpublicationdate());
		    list.add(map);
		}else {
			throw new NullPointerException("Books Record Not Found");
		}
		return list;	
	}

	@Override
	public String updateBookdetailByid(BooksRequest booksRequest,String bookid) {
		int noOfrecord;
		String status="";
		Books book=booksRepositoryImpl.findBookRecordByBookid(bookid);
		if(null==book) {
			throw new NullPointerException("Book Record Not Found With Bookid: "+bookid);
		}else {
		 noOfrecord=booksRepositoryImpl.updateBookRecord(booksRequest, bookid);
		 if(noOfrecord>0)
			 status="success";
		}
	 return status;	
	}

	@Override
	public String deleteBookdetailByid(String bookid) {
		String status="";
		Books book=booksRepositoryImpl.findBookRecordByBookid(bookid);
		if(null==book) {
		   throw new NullPointerException("Book Record Not Found With This BookId: "+bookid);
		}else {
			int noOfrecord=booksRepositoryImpl.deleteBookRecordBybookid(bookid);
			if(noOfrecord>0) {
			   status="success";
			}
		}
	  return status;	
	}
}
