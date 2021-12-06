package com.bookauthor.start.Request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public final class BooksRequest {
	private String bookid;
	private String booktitle;
	private String bookisbnNo;
	private int totalpage;
	private String bookrating;
	private double bookprice;
	private String bookpublicationdate;
}
