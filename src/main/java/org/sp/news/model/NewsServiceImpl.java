package org.sp.news.model;

import java.util.List;

import org.sp.news.domain.News;
import org.sp.news.exception.NewsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NewsServiceImpl implements NewsService{
	
	@Autowired
	private NewsDAO newsDAO;
	
	@Autowired
	private CommentsDAO commentsDAO;
	
	@Override
	public List selectAll() {
		return newsDAO.selectAll();
	}

	@Override
	public News select(int news_idx) {
		return newsDAO.select(news_idx);
	}

	@Override
	public void insert(News news) throws NewsException{
		newsDAO.insert(news);		
	}

	@Override
	public void update(News news)  throws NewsException{
		newsDAO.update(news);
	}

	
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(int news_idx)  throws NewsException{
		//딸려 있는 댓글도 삭제
		List commentsList = commentsDAO.selectAll(news_idx);
		
		if(commentsList.size() > 0) { //댓글이 있다면..
			commentsDAO.deleteByNewsIdx(news_idx);
		}
		
		//뉴스 기사 삭제
		newsDAO.delete(news_idx);
	}
	
}



