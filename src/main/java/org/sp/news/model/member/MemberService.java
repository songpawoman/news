package org.sp.news.model.member;

import java.util.List;

import org.sp.news.domain.Member;
import org.sp.news.domain.UserHobby;

public interface MemberService {
	public void regist(Member member, UserHobby userHobby); //가입 (회원+취미)
	public List selectAll();
	public Member select(Member member);
	public void update(Member member); //회원수정 
	public void remove(Member member); //회원탈퇴
}
