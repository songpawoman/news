package org.sp.news.controller;

import java.util.List;

import org.sp.news.domain.News;
import org.sp.news.exception.NewsException;
import org.sp.news.model.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NewsController {
	
	@Autowired
	private NewsService newsService;
	
	
	@GetMapping("/news/list")
	public ModelAndView getList() {
		//3단계:
		List newsList = newsService.selectAll();
		
		//4단계:  jsp에서 보여줄 것이 있을 경우, 결과 저장 
		ModelAndView mav = new ModelAndView("news/list");
		mav.addObject("newsList", newsList);
		
		return mav;
	}
	
	@GetMapping("/news/registform")
	public ModelAndView getForm() {
		
		ModelAndView mav = new ModelAndView("news/regist");
		return mav;
	}
	
	//글등록 
	@PostMapping("/news/regist")
	public ModelAndView regist(News news) {
		//3단계: 오라클에 넣기 
		newsService.insert(news);
		
		//4단계: 저장X  redirect
		ModelAndView mav = new ModelAndView("redirect:/news/list");
		return mav;
	}
	
	//게시물 한건 보기 요청 
	@GetMapping("/news/content")
	public ModelAndView getContent(int news_idx) {
		//3단계: 한건 가져오기 
		News news=newsService.select(news_idx);
		
		//4단계: jsp에서 보여줄 게시물을 저장
		ModelAndView mav = new ModelAndView("news/content");
		mav.addObject("news", news);
		
		return mav;
	}
	
	//한건 수정요청 
	@PostMapping("/news/edit")
	public ModelAndView edit(News news) {
		//3단계: 글 수정 
		newsService.update(news);
		
		//4단계: 다시 상세보기 재요청(redirect)
		ModelAndView mav = new ModelAndView("redirect:/news/content?news_idx="+news.getNews_idx());
		
		return mav;
	}
	
	//한건 삭제 
	@PostMapping("/news/del")
	public ModelAndView del(int news_idx) {
		//3단계:삭제 
		newsService.delete(news_idx);
		
		//4단계: 목록 다시 보여주기 redirect 
		ModelAndView mav = new ModelAndView("redirect:/news/list");
		return mav;
	}
	
	
	
	@ExceptionHandler(NewsException.class)
	public ModelAndView handle(NewsException e) {
		//발생한 예외 객체를 jsp에서 볼 수 있도록 결과 저장
		ModelAndView mav = new ModelAndView("error/result");
		mav.addObject("e", e);
		return mav;
	}
	
}





