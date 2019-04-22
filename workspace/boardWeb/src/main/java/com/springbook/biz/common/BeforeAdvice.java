package com.springbook.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

//@Service
//@Aspect
public class BeforeAdvice {


	
//	
//	public void beforeAdvice() {
//		System.out.println("[사전 처리] 비즈니스 로직 수행 전 동작");
//	}
	
	@Before("PointcutCommon.allPointcut()")
	public void beforeLog(JoinPoint jp) {
		String method = jp.getSignature().getName();  //비즈니스 메서드의 이름
		Object[] arg = jp.getArgs();   //비즈니스 메서드의 매개변수
		
		System.out.println("[사전 처리] " + method + "() 메소드 , arg 정보" + arg[0].toString() );
		
	}
}
