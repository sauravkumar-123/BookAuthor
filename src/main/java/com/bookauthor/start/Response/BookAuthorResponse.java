package com.bookauthor.start.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public final class BookAuthorResponse {
	    private boolean status;
	    private String message;
	    private Object data;
}
