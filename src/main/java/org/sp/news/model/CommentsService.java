package org.sp.news.model;

import java.util.List;

import org.sp.news.domain.Comments;

public interface CommentsService {
	public void insert(Comments comments);
	public List selectAll(int news_idx);
	public void deleteByNewsIdx(int news_idx);
}
