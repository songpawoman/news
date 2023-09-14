package org.sp.news.exception;

public class FileException extends RuntimeException{
	
	public FileException(String msg) {
		super(msg);
	}
	
	public FileException(String msg, Throwable e) {
		super(msg, e);
	}
	
}
