package org.sp.news.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NewsController {
	
	@GetMapping("/news/list")
	public ModelAndView getList() {
		
		ModelAndView mav = new ModelAndView("news/list");
		return mav;
	}
	
	@GetMapping("/news/registform")
	public ModelAndView getForm() {
		
		ModelAndView mav = new ModelAndView("news/regist");
		return mav;
	}
	
	//글등록 
	@PostMapping("/news/regist")
	public ModelAndView regist() {
		//3단계: 오라클에 넣기 
		
		//4단계: 저장X  redirect
		ModelAndView mav = new ModelAndView("redirect:/news/list");
		return mav;
	}
}





