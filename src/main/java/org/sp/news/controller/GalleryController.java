package org.sp.news.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sp.news.domain.Gallery;
import org.sp.news.domain.GalleryImg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GalleryController {
	private Logger logger=LoggerFactory.getLogger(this.getClass().getName());
	
	@GetMapping("/gallery/list")
	public ModelAndView getList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("gallery/list");
		
		return mav;
	}
	
	@GetMapping("/gallery/registform")
	public ModelAndView getForm(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("gallery/regist");
		
		return mav;
	}
	
	//글쓰기 요청 처리 
	@PostMapping("/gallery/regist")
	public ModelAndView regist(Gallery gallery, HttpServletRequest request) {
		logger.info("title = "+gallery.getTitle());
		logger.info("writer = "+gallery.getWriter());
		logger.info("content = "+gallery.getContent());
		
		//두개의 photo 중 파일이 채워져 있는 것만 출력 
		MultipartFile[] photoList=gallery.getFile();
		
		
		
		return null;
	}
	
}



