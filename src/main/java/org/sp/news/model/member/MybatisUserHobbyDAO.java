package org.sp.news.model.member;

import org.mybatis.spring.SqlSessionTemplate;
import org.sp.news.domain.UserHobby;
import org.sp.news.exception.UserHobbyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MybatisUserHobbyDAO implements UserHobbyDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public void insert(UserHobby userHobby) throws UserHobbyException {
		int result = sqlSessionTemplate.insert("UserHobby.insert", userHobby);
		if(result<1) {
			throw new UserHobbyException("취미 등록 실패");
		}
	}
	
}
