package org.sp.news.exception;

public class GalleryImgException extends RuntimeException{
	
	public GalleryImgException(String msg) {
		super(msg);
	}
	
	public GalleryImgException(String msg, Throwable e) {
		super(msg, e);
	}
	
}
