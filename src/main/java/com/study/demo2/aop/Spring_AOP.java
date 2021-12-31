package com.study.demo2.aop;

import com.study.demo2.config.SpringConfig;
import com.study.demo2.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Spring_AOP {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(SpringConfig.class);
        //理论值：根据接口获取实现类对象，但是与切入点表达式匹配，为了后续扩展方便，为其创建代对象
        UserService userService = context.getBean(UserService.class);
        //如果是实现类对象，则方法没有被扩展
        //如果是代理对象，则方法被扩展 oop有效的
        System.out.println(userService.getClass());
        userService.addUser();
    }
}
