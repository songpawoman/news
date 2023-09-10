package org.sp.news.model;

import java.util.List;

import org.sp.news.domain.Comments;

public interface CommentsDAO {

	public void insert(Comments comments);
	public List selectAll();
	
}
