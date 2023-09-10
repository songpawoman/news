package org.sp.news.domain;

import lombok.Data;

@Data
public class News {
	private int news_idx;
	private String title;
	private String writer;
	private String regdate;
	private int hit;
	private String content;
	
	
}
