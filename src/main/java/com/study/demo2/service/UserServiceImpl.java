package com.study.demo2.service;


import com.study.demo2.anno.CBB21;
import com.study.demo2.anno.Find;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Override
    @CBB21      //标记作用
    @Find(id = 200)
    public void addUser(){

        System.out.println("事务");
        int a = 1/0;
    }

    @Override
    @CBB21  //测试获取返回值的！！
    public int findCount(){
        return 1000;
    }
}
