package org.sp.news.model.member;

import java.util.List;

import org.sp.news.domain.Member;

public interface MemberService {
	public void regist(Member member); //가입 (회원+취미)
	public List selectAll();
	public Member select(Member member);//회원 정보 가져오기
	public Member loginCheck(Member member); //로그인 
	public void update(Member member); //회원수정 
	public void remove(Member member); //회원탈퇴
}
