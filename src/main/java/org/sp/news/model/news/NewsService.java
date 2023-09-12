package org.sp.news.model.news;

import java.util.List;

import org.sp.news.domain.News;

public interface NewsService {
	public List selectAll(); 
	public News select(int news_idx);
	public void insert(News news);
	public void update(News news);
	public void delete(int news_idx);
}
