package org.sp.news.model.news;

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
	public List selectAll(int news_idx) {
		return commentsDAO.selectAll(news_idx);
	}
	@Override
	public void deleteByNewsIdx(int news_idx) throws CommentsException{
		commentsDAO.deleteByNewsIdx(news_idx);		
	}
}
