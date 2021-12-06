package com.bookauthor.start.Utility;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bookauthor.start.Exception.BookAuthorException;

public class Utility {

	private static final Logger logger=LoggerFactory.getLogger(Utility.class);
	
	public static Timestamp StringdateToTimestamp(String stringdate) {
		try {
		      DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		      Date date = formatter.parse(stringdate);
		      Timestamp timeStampDate = new Timestamp(date.getTime());
		      logger.info("Timestam Datetime{}-> "+timeStampDate);
		      return timeStampDate;
		    } catch (ParseException e) {
		      e.printStackTrace();
		      logger.info("Exception While Convert String Date To Timestam{}-> "+e.getMessage());
		      throw new BookAuthorException("Unable To Convert Date");
		    }	
	}
}
