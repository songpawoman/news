package org.sp.news.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sp.news.exception.MemberException;

public class MemberLoginAspect {
	private Logger logger=LoggerFactory.getLogger(this.getClass().getName());
	
	public Object logionCheck(ProceedingJoinPoint joinPoint) throws MemberException, Throwable {
		Object result=null;
		
		//원래 호출하려던 컨트롤러의 메서드를 낚아 챈다.
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		logger.info("원래 호출하려면 메서드는 : "+targetName+"클래스의 "+methodName);
		
		//컨트롤러의 메서드에서 HttpServletRequest 를 통해 세션에 Member가 들어있는지 조사한다
		HttpServletRequest request=null;
		
		Object[] args = joinPoint.getArgs();
		
		for(Object obj : args) {
			if(obj instanceof HttpServletRequest) {
				request = (HttpServletRequest)obj;
			}
		}
		
		String uri = request.getRequestURI();
		
		if(true) { //검증하지 않아도 되는 요청 주소일 경우..
			logger.info("그냥 통과영역");
			result=joinPoint.proceed();
		}else {
			HttpSession session  = request.getSession();
			
			if(session.getAttribute("member")==null) {
				//없으면, 예외발생하여 적절한 예외페이지 보여준다 
				logger.info("세션 없는거 딸 걸림");
				throw new MemberException("로그인이 필요한 서비스입니다");
			}else {
				//있으면, 원래 가던길 가게한다
				logger.info("세션 잇으므로 통과");
				result = joinPoint.proceed();
			}
		}
		return result;
	}
}




