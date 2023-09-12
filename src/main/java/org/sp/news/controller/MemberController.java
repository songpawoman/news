package org.sp.news.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberController {
	
	//회원가입 양식 요청 
	@GetMapping("/member/registform")
	public ModelAndView getJoinForm() {
		ModelAndView mav = new ModelAndView("member/regist");
		return mav;
	}
	
	//로그인폼 요청 
	@GetMapping("/member/loginform")
	public ModelAndView getLoginForm() {
		ModelAndView mav = new ModelAndView("member/login");
		return mav;
	}
	
}
