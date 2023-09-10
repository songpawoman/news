package org.sp.news.exception;

public class NewsException extends RuntimeException{
	
	public NewsException(String msg) {
		super(msg);
	}
	
	public NewsException(String msg, Throwable e) {
		super(msg, e);
	}
	
}
