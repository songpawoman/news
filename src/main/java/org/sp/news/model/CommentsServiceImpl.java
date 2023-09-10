package org.sp.news.model;

import java.util.List;

import org.sp.news.domain.Comments;
import org.sp.news.exception.CommentsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentsServiceImpl implements CommentsService{
	@Autowired
	private CommentsDAO commentsDAO;

	@Override
	public void insert(Comments comments) throws CommentsException {
		commentsDAO.insert(comments);
	}

	@Override
	public List selectAll() {
		return commentsDAO.selectAll();
	}
	
}
