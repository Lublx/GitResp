package com.study.demo2.aop;

import com.study.demo2.anno.Find;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

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
     *
     *       3.切入点表达式案例练习：
     *       3.1 @Pointcut("bean(userServiceImpl)") 只匹配ID为userServiceImpl的对象
     *       @Pointcut("within(com.study.demo2.service.*)") 匹配xx.xx.service下的所有对象
     *       @Pointcut("@annotation(com.study.demo2.anno.CBB21)")  自定义注解
     */
    @Pointcut("@annotation(com.study.demo2.anno.CBB21)")
    public void pointcut(){

    }

    /**
     * 定义通知方法：
     *      1、前置通知  在目标方法执行之前执行
     *      2、后置通知  在目标方法执行之后执行
     *      3、异常通知  在目标方法执行之后抛出异常时执行
     *      4.、最终通知 都要执行的通知
     *      5、环绕通知  在目标方法执行前后都要执行的通知
     *
     *      记录程序的状态：
     *      1.目标对象的class/ 类路径
     *      2.目标对象的方法名
     *      3.目标对象的方法的参数信息
     *      4.获取目标对象方法的返回值
     *      5.获取目标对象执行报错的异常信息.
     */
    @Before("pointcut()")
    public void before(JoinPoint joinPoint){
        //1.获取目标对象的类型
        Class targetClass = joinPoint.getTarget().getClass();
        //2.获取目标对象的路径
        String path = joinPoint.getSignature().getDeclaringTypeName();
        //3.获取目标对象的方法名称
        String methodName = joinPoint.getSignature().getName();
        //4.获取方法参数
        Object[] args = joinPoint.getArgs();
    }
    //注意事项：如果多个参数，joinPoint必须位于第一位！
    @AfterReturning(value = "pointcut()",returning = "result")
    public void afterReturn(JoinPoint joinPoint,Object result){
        //如果需要获取当前的方法信息，则可以通过joinPoint获取
        System.out.println("我是后置通知，获取方法的返回值:"+result);
    }

    @Before("@annotation(find)")
    public void before2(Find find){
        System.out.println("ID的值为:"+find.id());
    }
}
