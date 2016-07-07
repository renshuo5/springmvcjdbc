package com.rs.aop;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.aop.MethodBeforeAdvice;

public class LoggerAdvice implements MethodBeforeAdvice {
	
	private Logger logger = Logger.getLogger(LoggerAdvice.class);
	
	public void before(Method method, Object[] args, Object target)
			throws Throwable {
		// TODO Auto-generated method stub
		// 类名
		String targetClassName = target.getClass().getName();
		// 获取被调用的方法名
		String targetMethodName = method.getName();
		// 显示日志的格式字符串
		String loginInfoText = "前置通知" + targetClassName + "类的"
				+ targetMethodName + "方法开始执行";
		logger.info(loginInfoText);
	}

}
