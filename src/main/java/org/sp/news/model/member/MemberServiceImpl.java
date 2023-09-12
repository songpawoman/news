package org.sp.news.model.member;

import java.util.List;

import org.sp.news.domain.Member;
import org.sp.news.domain.UserHobby;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private UserHobbyDAO userHobbyDAO;
	
	@Override
	public void regist(Member member, UserHobby userHobby) {
		
	}

	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member select(Member member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Member member) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Member member) {
		// TODO Auto-generated method stub
		
	}
	
}
