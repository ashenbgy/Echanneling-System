package com.echanneling.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ErrorResponse {
    private Date timestamp;
	private int statusCode;
    private String message;
    private String details;

	
}
