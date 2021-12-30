package com.study.demo1.service;

import org.springframework.stereotype.Service;
//代码的耦合度太高，不方便后期拓展
@Service //业务逻辑层
public class UserServiceImpl implements UserService{
    @Override
    public void addUser(){
        try {
            System.out.println("事务开始");
            System.out.println("完成用户新增");
            System.out.println("事务提交");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("事务回滚");
        }
    }
    public void deleteUser(){
        try {
            System.out.println("事务开始");
            System.out.println("完成用户删除操作");
            System.out.println("事务提交");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("事务回滚");
        }
    }
}
