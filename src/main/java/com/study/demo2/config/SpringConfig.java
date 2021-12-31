package com.study.demo2.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.study.demo2")
//让spring中的AOP生效
@EnableAspectJAutoProxy
public class SpringConfig {

}
