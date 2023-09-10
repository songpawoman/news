package org.sp.news.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.sp.news.domain.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MybatisNewsDAO implements NewsDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public News select(int news_idx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(News news) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(News news) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int news_idx) {
		// TODO Auto-generated method stub
		
	}
	
}
