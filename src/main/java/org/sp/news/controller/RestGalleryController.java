package org.sp.news.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sp.news.domain.Gallery;
import org.sp.news.domain.GalleryImg;
import org.sp.news.model.gallery.GalleryService;
import org.sp.news.model.util.FileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

//비동기 방식인 REST 요청에 대한 컨트롤러
//HTTP 표준 , 통신 시 URL을 원래 HTTP 프로토콜의 의도에 맞게 표기하자!!(박사 학위 논문) 
//  /gallery/regist    /gallery/update   /gallery/delete
//  /gallery/regist.jsp    /gallery/update.jsp   /gallery/delete.jsp
// RESTful (REST 규칙을 준수한 URL표기)
//  /gallery  (method=post)  ,  /gallery (method=put)

@RestController
public class RestGalleryController {
	private Logger logger=LoggerFactory.getLogger(this.getClass().getName());
	
	@Autowired
	private GalleryService galleryService;
	
	@Autowired
	private FileManager fileManager;
	
	
	//등록 요청 처리 
	
	@PostMapping("/rest/gallery/regist")
	public ResponseEntity regist(Gallery gallery, HttpServletRequest request) {
		logger.info("title = "+gallery.getTitle());
		logger.info("writer = "+gallery.getWriter());
		logger.info("content = "+gallery.getContent());
		
		MultipartFile[] file = gallery.getFile();
		
		//Gallery DTO가 보유할 List 생성
		List galleryImgList = new ArrayList<GalleryImg>();
		
		
		//서버의 저장소에 파일 저장
		ServletContext context=request.getSession().getServletContext();
		String realpath=context.getRealPath("/resources/data/");
		
		logger.info("realpath : "+realpath);
		
		for(MultipartFile photo : file) {
			String filename=photo.getOriginalFilename(); //원래 파일명
			
			filename=fileManager.createFilename(filename); //개발자가 지정한 파일명.. 473284327.jpg
			
			GalleryImg galleryImg = new GalleryImg();
			galleryImg.setFilename(filename);
			galleryImgList.add(galleryImg);
			logger.info("filename = "+filename);
			
			File f = new File(realpath+filename);
			
			try {
				photo.transferTo(f);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		gallery.setGalleryImgList(galleryImgList); //Gallery에 대입
		
		galleryService.regist(gallery); 
		
		
		ResponseMessage message = new ResponseMessage();
		message.setMsg("등록 성공");
		ResponseEntity<ResponseMessage> entity = new ResponseEntity<ResponseMessage>(HttpStatus.OK);
		
		return entity;		
	}

}










