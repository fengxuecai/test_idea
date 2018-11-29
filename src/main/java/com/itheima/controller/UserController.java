package com.itheima.controller;


import com.itheima.domain.User;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findAll")
    public String findAll(Model model){
        long start = System.currentTimeMillis();
        List<User> users = userService.findAllRedis();
        long end = System.currentTimeMillis();
        System.out.println("总耗时:"+(end-start)+"毫秒");
        model.addAttribute("users",users);
        return "success";
    }
}
