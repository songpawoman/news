package org.sp.news.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sp.news.domain.Member;
import org.sp.news.exception.EmailException;
import org.sp.news.exception.MemberException;
import org.sp.news.exception.UserHobbyException;
import org.sp.news.model.member.HobbyService;
import org.sp.news.model.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberController {
	
	private Logger logger=LoggerFactory.getLogger(this.getClass().getName());
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private HobbyService hobbyService;
	
	
	//메인요청 처리
	@GetMapping("/")
	public ModelAndView main() {
		ModelAndView mav = new ModelAndView("index");
		
		return mav;
	}
	
	//회원가입 양식 요청 
	@GetMapping("/member/registform")
	public ModelAndView getJoinForm() {
		//3단계: 취미 가져오기
		List hobbyList=hobbyService.selectAll();
		
		//4단계: 회원가입폼 jsp로 가져갈 것이 있다. 
		ModelAndView mav = new ModelAndView("member/regist");
		mav.addObject("hobbyList", hobbyList);
		
		return mav;
	}
	
	//로그인폼 요청 
	@GetMapping("/member/loginform")
	public ModelAndView getLoginForm() {
		ModelAndView mav = new ModelAndView("member/login");
		
		return mav;
	}
	
	
	
	
	//가입요청 처리 
	@PostMapping("/member/regist")
	public ModelAndView regist(Member member, String email_id, String email_server) {
		//아이디, 패스워드, 이름, 이메일, 수신동의,취미 
		member.setEmail(email_id+"@"+email_server); //zino1187@naver.com
		
		logger.info("메일주소는 "+member.getEmail());
		logger.info("취미는 "+member.getHobby_idx().length);
		
		//3단계: 회원등록 
		memberService.regist(member);

		//4단계: 로그인 페이지 보여주기  redirect:/member/loginform
		ModelAndView mav = new ModelAndView("redirect:/member/loginform");
		return mav;
	}
	
	@ExceptionHandler(MemberException.class)
	public ModelAndView handle(MemberException e) {
		ModelAndView mav = new ModelAndView("error/result");
		mav.addObject("e", e);
		return mav;
	}
	
	
	@ExceptionHandler(UserHobbyException.class)
	public ModelAndView handle(UserHobbyException e) {
		ModelAndView mav = new ModelAndView("error/result");
		mav.addObject("e", e);
		return mav;
	}
	
	@ExceptionHandler(EmailException.class)
	public ModelAndView handle(EmailException e) {
		ModelAndView mav = new ModelAndView("error/result");
		mav.addObject("e", e);
		return mav;
	}
	
	
}









