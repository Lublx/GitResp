package com.study.demo2.aop;

import com.study.demo2.anno.Find;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component  //将当前类交给Spring容器管理
@Aspect          //标识一个切面类
@Order(1)       //可以利用order关键字 实现AOP的排序 数字越小优先级越高
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
    /*@AfterThrowing(value = "pointcut()",throwing = "exception")
    public void afterThrow(JoinPoint joinPoint,Exception exception){
        //如果需要获取当前的方法信息，则可以通过joinPoint获取
        System.out.println("我是异常通知:"+ exception.getMessage());
    }*/

    //环绕通知

    /**
     * 环绕通知：
     *  特点：
     *          1.方法执行前后，通知都要执行
     *          2.环绕通知可以控制目标方法是否执行
     *          3.环绕通知必须添加返回值
     *   proceed()
     *           作用1：如果有下一个通知，则执行下一个通知
     *           作用2：如果没有下一个通知，则执行目标方法
     */

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("环绕通知开始！！！");
        Object result = joinPoint.proceed();
        System.out.println("环绕通知结束！！");
        return result;
    }


    //最终通知
    @After("pointcut()")
    public void after(){
        System.out.println("最终通知方法！");
    }

    /* 异常通知和后置通知只能存在一个 因为彼此互斥

    @AfterReturning(value = "pointcut()",returning = "result")
    public void afterReturn(JoinPoint joinPoint,Object result){
        //如果需要获取当前的方法信息，则可以通过joinPoint获取
        System.out.println("我是后置通知，获取方法的返回值:"+result);

        代码解释：
            1.@annotation(find) 拦截find变量名称对应类型的注解
            2.当匹配该注解之后，将注解对象当做参数传递给find
            优势：可以一步到位获取注解的内容，避免了反射的代码
    }*/

    @Before("@annotation(find)")
    public void before2(Find find){
        System.out.println("ID的值为:"+find.id()
        );
    }
}
