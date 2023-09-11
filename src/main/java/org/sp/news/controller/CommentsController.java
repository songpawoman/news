package org.sp.news.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sp.news.domain.Comments;
import org.sp.news.exception.CommentsException;
import org.sp.news.model.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CommentsController {
	private Logger logger=LoggerFactory.getLogger(this.getClass().getName());
	
	@Autowired
	private CommentsService commentsService;
	
	
	//게시물 목록 요청 
	@GetMapping("/comments/list")
	@ResponseBody
	public List<Comments> getList() {
		//반환값이 ModelAndView 이거나 String 이면,  InternalResourceViewResolver 가 동작하여 
		//접두어와 접미어를 조합하여  jsp명을 반환
		//이때 ResponseBody 어노테이션을 적용하면, ViewResolver가 동작하지 않게 됨
		//3단계 : 리스트 가져오기 
		List commentsList = commentsService.selectAll();
		
		//java의 List를 자동으로 String json 문자열로 변환하려면, 원래 GSON 같은 외부 라이브러리 들이 필요하다..
		//하지만, 스프링에서는 이 GSON을 사용하지 않아도 내부적으로 json 문자열로 자동 변환하는 기능을 지원..
		
		return commentsList;
	}
	
	//댓글 등록 요청 
	@PostMapping("/comments/regist")
	@ResponseBody
	public String regist(Comments comments) {
		logger.info("넘겨받은 comments is "+comments);
		//3단계:
		commentsService.insert(comments);
		return "ok";
	}
	
	@ExceptionHandler(CommentsException.class)
	@ResponseBody
	public String handle(CommentsException e) {
		return e.getMessage();
	}
	
}









