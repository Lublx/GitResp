package com.study.demo2.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) //注解对方法有效
@Retention(RetentionPolicy.RUNTIME) //运行时有效
public @interface CBB21 {                         //注解起标记作用

}
