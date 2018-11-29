package com.itheima.service.impl;

import com.itheima.anno.CheckRedis;
import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao dao;

    @CheckRedis
    public List<User> findAllRedis() {
        return dao.findAll();
    }
}
