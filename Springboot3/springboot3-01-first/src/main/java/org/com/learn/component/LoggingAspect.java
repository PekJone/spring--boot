package org.com.learn.component;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-07  10:03
 */
@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    //定义切入点，匹配所有以"service"结尾的包下的所有方法
    @Pointcut(value = "execution(* org.com.learn.service..*(..))")
    public void serviceMethods(){
        System.out.println("我也执行了");
    }

    @Before("serviceMethods()")
    public void loggerBefore(JoinPoint joinPoint){
        System.out.println("我执行了切面方法");
         String methodName = joinPoint.getSignature().getName();
         Object[] args = joinPoint.getArgs();
         logger.info("method [{}] with arguments [{}] is called",methodName,args);
    }
}
