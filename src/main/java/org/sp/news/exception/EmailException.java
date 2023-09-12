package org.sp.news.exception;

public class EmailException extends RuntimeException{
	
	public EmailException(String msg) {
		super(msg);
	}
	
	public EmailException(String msg, Throwable e) {
		super(msg, e);
	}
	
}
