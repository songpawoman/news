package org.sp.news.exception;

public class UserHobbyException extends RuntimeException{
	
	public UserHobbyException(String msg) {
		super(msg);
	}
	
	public UserHobbyException(String msg, Throwable e) {
		super(msg, e);
	}
	
}
