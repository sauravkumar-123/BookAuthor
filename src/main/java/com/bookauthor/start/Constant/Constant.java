package com.bookauthor.start.Constant;

public final class Constant {

	public static final String findallbookQuery="SELECT * FROM Books OREDER BY totalpage";
	public static final String findSinglebookQuery="SELECT * FROM Books Where bookid=?";
	public static final String insertbookrecordQuery="INSERT INTO Books (bookid,booktitle,bookisbnNo,totalpage,bookrating,bookprice,bookpublicationdate) VALUES (?,?,?,?,?,?,?)";
	public static final String updatebookrecordQuery="UPDATE Books SET booktitle=?, totalpage=?, bookrating=?, bookprice=?, bookpublicationdate=? WHERE bookid=?";
	public static final String deletebookrecordQuery="DELETE FROM Books WHERE bookid=?";
}
