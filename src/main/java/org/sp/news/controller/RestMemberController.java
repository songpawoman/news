package org.sp.news.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.sp.news.domain.Member;
import org.sp.news.exception.MemberException;
import org.sp.news.model.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/*
 * 기존 Controller와 큰 차이점은 없다, 단지 비동기 방식으로 요청이 들어올때 
 * 모든 메서드에 기본적으로 @ResponseBody가 자동 처리
 * 또한 이 컨트롤러는 REST(representational State Transfer) API에 최적화 되어 있다.
 * */

@RestController
public class RestMemberController {
	
	@Autowired
	private MemberService memberService;
	
	//로그인 요청 처리 
	@PostMapping("/rest/member/login")
	public ResponseEntity login(@RequestBody Member member, HttpServletRequest request) {
		//logger.info("넘겨받은 id "+member.getId());
		//logger.info("넘겨받은 pass "+member.getPass());
		Member dto = memberService.loginCheck(member);
		
		HttpSession session=request.getSession();
		session.setAttribute("member", dto); //세션에 담아두기
		
		//HTTP응 응답정보를 보다, 자세하고 체계적으로 보내보자 
		ResponseMessage msg = new ResponseMessage();
		msg.setMsg("로그인이 성공되었습니다");
		
		ResponseEntity<ResponseMessage> entity=new ResponseEntity<ResponseMessage>(msg, HttpStatus.OK);
		
		return entity; 
	} 
	
	@ExceptionHandler(MemberException.class)
	public ResponseEntity handle(MemberException e) {
		//HTTP응 응답정보를 보다, 자세하고 체계적으로 보내보자 
		ResponseMessage msg = new ResponseMessage();
		msg.setMsg(e.getMessage());
		
		ResponseEntity<ResponseMessage> entity=new ResponseEntity<ResponseMessage>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		
		return entity; 

	}
	
}






