package com.lectopia.lab.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAop {
	
	/*@Pointcut("within(com.lectopia.lab.persistence.*)")
	private void pointcutMethod() {
			
	}
	
	@Around("pointcutMethod()")
	public Object loggerAop(ProceedingJoinPoint jointPoint) throws Throwable{
		String signatureStr = jointPoint.getSignature().toShortString();
		System.out.println(signatureStr+" start.");
		System.out.println("1. --- 로그 작업 처리 ---"+System.currentTimeMillis());
		
		try {
			Object obj = jointPoint.proceed();
			return obj;
		}finally {
			System.out.println("2. --- After proceed ---"+System.currentTimeMillis());
			System.out.println(signatureStr+" end.");
		}
	}
	
	@Before("within(com.lectopia.lab.persistence.*)")
	public void beforeMethod() {
		System.out.println("beforeMethod() called");
	}*/
	
	@Pointcut("execution(* com.lectopia.lab.service.BoardServiceImpl.get(..))")
	public void logging() {
		System.out.println("Test : "+System.currentTimeMillis());
	}
	
	@Before("logging()")
	public void before() {
		System.out.println("* before *");
		//common job
		//LOG, TX
	}
	
	@Around("execution(* com..controller.*Controller.*(..)) or" + 
	"execution(* com..service.*Impl.*(..)) or"+
	"execution(* com..persistence.*Mapper.*(..))")
	public Object logPrint(ProceedingJoinPoint joinPoint)throws Throwable {
		String type = joinPoint.getSignature().getDeclaringTypeName();
		String name="";
		if(type.indexOf("Controller") > -1) {
			name="Controller \t: ";
		}else if(type.indexOf("Service") > -1) {
			name="ServiceImpl \t: ";
		}else if(type.indexOf("DAO") > -1)
		{
			name="DAO \t: ";
		}
		
		System.out.println(name+type+"."+joinPoint.getSignature().getName()+"()");
		return joinPoint.proceed();
	}
}
