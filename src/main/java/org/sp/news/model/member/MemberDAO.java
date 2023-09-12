package org.sp.news.model.member;

import java.util.List;

import org.sp.news.domain.Member;

public interface MemberDAO {
	public List selectAll();
	public void insert(Member member);
	public Member select(Member member); //회원1명 가져오기
}
