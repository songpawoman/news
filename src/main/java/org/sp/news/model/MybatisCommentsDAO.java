package org.sp.news.model;

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
	public List selectAll() {
		return sqlSessionTemplate.selectList("Comments.selectAll");
	}
	
}