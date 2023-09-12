package org.sp.news.model.news;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.sp.news.domain.News;
import org.sp.news.exception.NewsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MybatisNewsDAO implements NewsDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	

	@Override
	public List selectAll() {
		return sqlSessionTemplate.selectList("News.selectAll");
	}

	@Override
	public News select(int news_idx) {
		return sqlSessionTemplate.selectOne("News.select", news_idx);
	}

	@Override
	public void insert(News news) throws NewsException{
		int result = sqlSessionTemplate.insert("News.insert", news);
		if(result<1) {
			throw new NewsException("뉴스 기사 등록 실패");
		}
		
	}

	@Override
	public void update(News news) throws NewsException{
		int result = sqlSessionTemplate.update("News.update", news);
		if(result<1) {
			throw new NewsException("글 수정에 실패");
		}
	}

	@Override
	public void delete(int news_idx)  throws NewsException{
		int result = sqlSessionTemplate.delete("News.delete", news_idx);
		if(result<1) {
			throw new NewsException("삭제에 실패");
		}
	}
	
}
