package org.sp.news.model;

import java.util.List;

import org.sp.news.domain.News;
import org.sp.news.exception.NewsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl implements NewsService{
	
	@Autowired
	private NewsDAO newsDAO;
	
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

	@Override
	public void delete(int news_idx)  throws NewsException{
		newsDAO.delete(news_idx);
	}
	
}
