package com.itheima.test;


import com.itheima.domain.User;
import com.itheima.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationConfig.xml")
public class TestRedisAnno {

    @Autowired
    private UserService userService;

    @Test
    public void testRedisTime(){
        long start = System.currentTimeMillis();
        List<User> users = userService.findAllRedis();
        long end = System.currentTimeMillis();
        System.out.println("查询用了"+(end-start)+"毫秒");
        System.out.println(users);
    }
}
