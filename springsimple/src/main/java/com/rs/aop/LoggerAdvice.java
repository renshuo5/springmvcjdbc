package com.rs.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;

public class LoggerAdvice {

	private Logger logger = Logger.getLogger(LoggerAdvice.class);

	public void checkValidity() {
		logger.info("------------------验证合法性----------------");
		System.out.println("------------------验证合法性----------------");
		
	}

	public void addLog(JoinPoint j) {
		logger.info("------------------添加日志----------------");
		System.out.println("------------------添加日志----------------");
		Object obj[] = j.getArgs();
		for (Object o : obj) {
			System.out.println(o);
		}
		logger.info("========checkSecurity==" + j.getSignature().getName());// 这个是获得方法名
		System.out.println("========checkSecurity==" + j.getSignature().getName());
	}

}
