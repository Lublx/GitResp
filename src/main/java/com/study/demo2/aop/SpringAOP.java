package com.study.demo2.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component  //将当前类交给Spring容器管理
@Aspect          //标识一个切面类
public class SpringAOP {

    /**
     *      切面 = 切入点表达式 + 通知方法
     *      1.切入点
     *          理解：可以理解为就是一个if判断
     *          判断条件：切入点表达式
     *          规则：
     *                  如果满足表达式 则判断为true，则执行通知方法
     *                  如果不满足表达式 则判断为false 则不执行通知方法
     *
     *       2.切入点表达式
     *       2.1 bean("对象的id")
     *          2.1.1每次拦截，只拦截1个
     *       2.2 within("包名.类名")
     *       2.3 execution(返回值类型    包名.类名.方法名(参数列表))
     *       2.4 @annotation（注解的路径）
     */
    @Pointcut("bean(userServiceImpl)")
    public void pointcut(){

    }

    /**
     * 定义通知方法：
     *      1、前置通知  在目标方法执行之前执行
     *      2、后置通知  在目标方法执行之后执行
     *      3、异常通知  在目标方法执行之后抛出异常时执行
     *      4.、最终通知 都要执行的通知
     *      5、环绕通知  在目标方法执行前后都要执行的通知
     */
    @Before("pointcut()")
    public void before(){
        System.out.println("你好，我是前置通知");
    }
}
