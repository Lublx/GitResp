package com.study.demo1.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy {
    /**
     * 获取代理对象
     * 参数说明:
     *  1. ClassLoader loader 类加载器 读取真实的类数据
     *  2. Class<?>[] interfaces, 要求传递接口信息
     *  3. InvocationHandler h  当代理对象执行方法时 执行
     * 注意事项: JDK代理必须要求 "被代理者"要么有接口(本身就是接口),要么实现接口(实现类).
     */

    public static Object getProxy(Object target){
        //1.获取类加载器
        ClassLoader classLoader = target.getClass().getClassLoader();
        //2.获取接口
        Class[] interfaces = target.getClass().getInterfaces();
        return Proxy.newProxyInstance(classLoader,interfaces,getInvocationHandler(target));
    }

    //代理对象执行方法时调用
    public static InvocationHandler getInvocationHandler(Object target){
        //这些代码都是写死的!!!!!!
        return new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("事务开始");
                //执行真实的业务方法
                Object result = method.invoke(target,args);
                System.out.println("事务提交");
                return result;
            }
        };
    }
}
