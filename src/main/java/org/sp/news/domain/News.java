package org.sp.news.domain;

import java.util.List;

import lombok.Data;

@Data
public class News {
	private int news_idx;
	private String title;
	private String writer;
	private String regdate;
	private int hit;
	private String content;
	
	//하나의 뉴스는 자식 댓글을 여러개 보유할 수 있다.. 
	private List<Comments> commentsList;
}
