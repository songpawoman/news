package org.sp.news.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sp.news.domain.Gallery;
import org.sp.news.model.gallery.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GalleryController {
	private Logger logger=LoggerFactory.getLogger(this.getClass().getName());
	
	@Autowired
	private GalleryService galleryService;
	
	
	@GetMapping("/gallery/list")
	public ModelAndView getList(HttpServletRequest request) {
		//3단계: 모든 레코드 가져오기
		List galleryList = galleryService.selectAll();
		
		
		//4단계: jsp에서 볼 결과 저장
		ModelAndView mav = new ModelAndView("gallery/list");
		mav.addObject("galleryList", galleryList);
		
		return mav;
	}
	
	@GetMapping("/gallery/registform")
	public ModelAndView getForm(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("gallery/regist");
		
		return mav;
	}
	
	//상세보기 요청 처리 
	@GetMapping("/gallery/content")
	public ModelAndView getContent(int gallery_idx, HttpServletRequest request) {
		//3단계: 한건 가져오기 
		Gallery gallery = galleryService.select(gallery_idx);
		
		//4단계: jsp로 가져갈 것이 있으므로 ,저장 
		ModelAndView mav = new ModelAndView("gallery/content");
		mav.addObject("gallery", gallery);
		
		return mav;
	}
	
}









