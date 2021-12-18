package com.bookauthor.start.Model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Books_Data")
public class Books implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="SL_No")
	private long slno;
	
	@Column(name = "Book_Id",length = 10)
	private String bookid;
	
	@Column(name="Book_Title",length = 30)
	private String booktitle;
	
	@Column(name = "Book_ISBN",length = 20)
	private String bookisbnNo;
	
	@Column(name = "Book_Total_page")
	private int totalpage;
	
	@Column(name = "Book_Ratings",length = 15)
	private String bookrating;
	
	@Column(name = "Book_Price")
	private double bookprice;
	
	@Column(name="Book_Publication_Date")
	private Timestamp bookpublicationdate;
}
