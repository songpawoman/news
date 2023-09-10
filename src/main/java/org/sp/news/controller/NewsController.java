package org.sp.news.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NewsController {
	
	@GetMapping("/news/list")
	public ModelAndView getList() {
		
		ModelAndView mav = new ModelAndView("news/list");
		return mav;
	}
}





