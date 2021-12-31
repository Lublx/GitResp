package com.study.demo2.service;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Override
    public void addUser(){
        System.out.println("事务");
    }
}
