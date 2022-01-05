package com.study.demo2.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component      //将当前类 交给Spring容器管理
@Aspect              //标识AOP
@Order(2)
public class SpringAOP2 {
    @Around("@annotation(com.study.demo2.anno.CBB21)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("开始！2");
        Object result = joinPoint.proceed();
        System.out.println("结束！2");
        return result;
    }
}
