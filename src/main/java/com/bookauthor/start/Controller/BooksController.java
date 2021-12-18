package com.bookauthor.start.Controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookauthor.start.Request.BooksRequest;
import com.bookauthor.start.Response.BookAuthorResponse;
import com.bookauthor.start.Service.BooksService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved all records"),
        @ApiResponse(code = 201, message = "Successfully created a record"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
        @ApiResponse(code = 500, message = "Application failed to process the request!!Internal Server Error")
}
)
@Api(value = "BookAuthorController" ,description = "This is BookAuthor Controller for Books CRUD API",produces = "application/json")
@RestController
@RequestMapping(value = "/v1/booksauthor/api")
public class BooksController {

	private static final Logger logger=LoggerFactory.getLogger(BooksController.class);
	
	@Autowired
	private BooksService booksService;
	
	@PostMapping("/addbooks")
	@ApiOperation(value = "Create a new book record", response = ResponseEntity.class)
	public ResponseEntity<BookAuthorResponse> addBookdetails(@RequestBody BooksRequest booksRequest){
	logger.info("Books Request Payload{}->"+booksRequest);
	if((null!=booksRequest.getBooktitle() && !booksRequest.getBooktitle().isEmpty()) &&
	   (null!=booksRequest.getBookid() && !booksRequest.getBookid().isEmpty()) &&		
	   (null!=booksRequest.getBookisbnNo() && !booksRequest.getBookisbnNo().isEmpty()) &&
	   (null!=booksRequest.getBookpublicationdate() && !booksRequest.getBookpublicationdate().isEmpty()) &&
	   (0!=booksRequest.getTotalpage())) {
		String status=booksService.addBookdetails(booksRequest);
		if(status.equalsIgnoreCase("success")) {
			return new ResponseEntity<BookAuthorResponse>(new BookAuthorResponse(true,"Book Record Inserted", booksRequest), HttpStatus.CREATED);
		}
	}else {
		return new ResponseEntity<BookAuthorResponse>(new BookAuthorResponse(false,"Invalid Input Parameter", null), HttpStatus.BAD_REQUEST);
	}
	return new ResponseEntity<BookAuthorResponse>(new BookAuthorResponse(false,"Failed To Insert Book Record", null), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/getallbooksrecord")
	@ApiOperation(value = "Get all book record", response = ResponseEntity.class)
	public ResponseEntity<BookAuthorResponse> getAllBookRecord(){
	List<Map<String, Object>> booksList=booksService.getAllBookdetails();
	logger.info("Books Record{}->"+booksList);
	if(null!=booksList&&!booksList.isEmpty()) {
		return new ResponseEntity<BookAuthorResponse>(new BookAuthorResponse(true,"Books Record Fetched", booksList), HttpStatus.OK);
	}else {
		return new ResponseEntity<BookAuthorResponse>(new BookAuthorResponse(false,"Books Record Not Found", null), HttpStatus.NOT_FOUND);
	}
	}
	
	@GetMapping("/getbookrecord/{bookid}")
	@ApiOperation(value = "Get all book record", response = ResponseEntity.class)
	public ResponseEntity<BookAuthorResponse> getSingleBookRecord(@PathVariable(value = "bookid") String bookid){
	List<Map<String, Object>> booksList=booksService.getBookdetailByid(bookid);
	logger.info("Book Record{}->"+booksList);
	if(null!=booksList&&!booksList.isEmpty()) {
		return new ResponseEntity<BookAuthorResponse>(new BookAuthorResponse(true,"Book Record Fetched", booksList), HttpStatus.OK);
	}else {
		return new ResponseEntity<BookAuthorResponse>(new BookAuthorResponse(false,"Book Record Not Found", null), HttpStatus.NOT_FOUND);
	}
	}
	
	@PutMapping("/updatebookrecord/{bookid}")
	@ApiOperation(value = "Update a book record", response = ResponseEntity.class)
	public ResponseEntity<BookAuthorResponse> updateSingleBookRecord(@PathVariable(value = "bookid") String bookid,@RequestBody BooksRequest booksRequest){
	logger.info("Books Request Payload{}->"+booksRequest);
	if((null!=booksRequest.getBooktitle() && !booksRequest.getBooktitle().isEmpty()) &&
	   (null!=bookid && !bookid.isEmpty()) &&		
	   (null!=booksRequest.getBookpublicationdate() && !booksRequest.getBookpublicationdate().isEmpty()) &&
	   (0!=booksRequest.getTotalpage())) {
	   String status=booksService.updateBookdetailByid(booksRequest, bookid);
	   if(status.equalsIgnoreCase("success")) {
			return new ResponseEntity<BookAuthorResponse>(new BookAuthorResponse(true,"Book Record Updated", booksRequest), HttpStatus.CREATED);
		}
	}else {
		   return new ResponseEntity<BookAuthorResponse>(new BookAuthorResponse(false,"Invalid Input Parameter", null), HttpStatus.BAD_REQUEST);
	}
	return new ResponseEntity<BookAuthorResponse>(new BookAuthorResponse(false,"Failed To Update Book Record", null), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping("/deletebookrecord/{bookid}")
	@ApiOperation(value = "Delete a book record", response = ResponseEntity.class)
	public ResponseEntity<BookAuthorResponse> deleteBookRecordByid(@PathVariable(value = "bookid") String bookid){
	String status=booksService.deleteBookdetailByid(bookid);
	if(null!=status && status.equalsIgnoreCase("success")) {
		return new ResponseEntity<BookAuthorResponse>(new BookAuthorResponse(true,"Book Record Deleted", status), HttpStatus.OK);	
	}else {
		return new ResponseEntity<BookAuthorResponse>(new BookAuthorResponse(true,"Failed To Delete Bookrecord", status), HttpStatus.EXPECTATION_FAILED);
	}
	}
}
