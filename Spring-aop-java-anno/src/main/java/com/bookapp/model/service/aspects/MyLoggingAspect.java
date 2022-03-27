package com.bookapp.model.service.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Aspect
@Service
public class MyLoggingAspect {

	private static Logger logger = LoggerFactory.getLogger(MyLoggingAspect.class);

	@Around("@annotation(MyLogger)")
	public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();

		Object object = joinPoint.proceed();
		long end = System.currentTimeMillis();

		logger.info("method take :" + (end - start) + " ms time to execute");

		return object;
	}
}
