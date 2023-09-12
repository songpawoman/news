package org.sp.news.model.member;

import java.util.List;

import org.sp.news.domain.Hobby;
import org.sp.news.domain.Member;
import org.sp.news.domain.UserHobby;
import org.sp.news.exception.MemberException;
import org.sp.news.exception.UserHobbyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberDAO memberDAO;

	@Autowired
	private UserHobbyDAO userHobbyDAO;
	
	
	//회원가입 
	@Transactional(propagation = Propagation.REQUIRED)
	public void regist(Member member) throws MemberException, UserHobbyException{
		//Member 테이블에 넣기 
		
		//insert 하기 전에는 member_idx=0
		memberDAO.insert(member);
		//insert 하기 전에는 member_idx는 시퀀스 값이 채워져 있슴
		
		//취미선택 테이블에 넣기
		UserHobby dto = new UserHobby();
		dto.setMember(member);//어떤 회원을?
		
		//선택한 취미 만큼..
		for(int i=0;i<member.getHobby_idx().length;i++) {
			Hobby hobby = new Hobby();
			hobby.setHobby_idx(member.getHobby_idx()[i]);
			dto.setHobby(hobby);//어떤 취미?
			userHobbyDAO.insert(dto);
		}
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
