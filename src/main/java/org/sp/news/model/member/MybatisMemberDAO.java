package org.sp.news.model.member;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.sp.news.domain.Member;
import org.sp.news.exception.MemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MybatisMemberDAO implements MemberDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List selectAll() {
		return sqlSessionTemplate.selectList("Member.selectAll");
	}

	public void insert(Member member) throws MemberException{
		int result = sqlSessionTemplate.insert("Member.insert", member);
		if(result <1) {
			throw new MemberException("회원 등록 실패");
		}
		
	}

	@Override
	public Member select(Member member) {
		return sqlSessionTemplate.selectOne("Member.select", member);
	}
	
}
