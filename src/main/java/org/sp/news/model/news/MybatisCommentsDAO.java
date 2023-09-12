package org.sp.news.model.news;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.sp.news.domain.Comments;
import org.sp.news.exception.CommentsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MybatisCommentsDAO implements CommentsDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public void insert(Comments comments) throws CommentsException{
		int result = sqlSessionTemplate.insert("Comments.insert", comments);
		if(result<1) {
			throw new CommentsException("댓글 등록 실패");
		}
	}

	@Override
	public List selectAll(int news_idx) {
		return sqlSessionTemplate.selectList("Comments.selectAll", news_idx);
	}
	
	@Override
	public void deleteByNewsIdx(int news_idx) throws CommentsException{
		int result = sqlSessionTemplate.delete("Comments.deleteByNewsIdx", news_idx);
		if(result <1) {
			throw new CommentsException("댓글 삭제 실패");
		}
	}
}
