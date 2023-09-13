package org.sp.news.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sp.news.domain.Member;
import org.sp.news.exception.MemberException;

public class MemberLoginAspect {
	private Logger logger=LoggerFactory.getLogger(this.getClass().getName());
	
	//ProceedingJoinPoint 는 원래 호출하려면 타겟 클래스 및 메서드, 매개변수 각종 정보들이 들어있다..
	public Object loginCheck(ProceedingJoinPoint joinPoint) throws MemberException, Throwable {
		//1) 클라이언트의 요청을 낚아 챈다 
		String className = joinPoint.getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		
		logger.info("클라이언트가 원래 호출하려고 했던 클래스는 "+className);
		logger.info("클라이언트가 원래 호출하려고 했던 메서드는 "+methodName);
		
		Object[] args=joinPoint.getArgs();
		
		HttpServletRequest request=null;
		
		for(Object obj : args) {
			logger.info("클라이언트가 원래 호출하려고 했던 메서드의 매개변수는 "+obj);
			
			if(obj instanceof HttpServletRequest) { 
				request = (HttpServletRequest)obj;
			}			
		}
		
		String uri = request.getRequestURI();
		
		if(
			uri.equals("/") || 								//메인페이지 요청		
			uri.equals("/member/loginform") ||  	//로그인폼 요청
			uri.equals("/rest/member/login") ||  	//로그인 요청			
			uri.equals("/member/registform")|| 	//가입폼 요청
			uri.equals("/member/regist"))    			//가입요청
		{ //로그인 인증을 판단할 필요가 없는 경우... 웹사이트 메인, 오시는 길, 회사소개..
			joinPoint.proceed(); //원래 호출하려던 메서드 호출..즉 가던길 가도록..
		}else { //로그인 검증이 필요한 경우..
			//2) 낚아챈 클라이언트의 요청 객체로부터 세션을 얻어와 해당 세션에 member가 들어있지 않다면, 즉 로그인 인증을 받지 
			//않은 요청이라면, MemberException 예외를 발생
			HttpSession session=request.getSession();
			Member member = (Member)session.getAttribute("member");
			
			if(member == null) { //로그인 인증이 없을 경우...
				throw new MemberException("로그인이 필요한 서비스입니다");
			}else {
				joinPoint.proceed();//가던 길 그대로 가게...
			}
		}
		//인증을 받았다면 그냥 가던 길 가도록...
		
		Object result=joinPoint.proceed(); //원래 호출하려면 메서드 호출
		
		return result;
	}
}

