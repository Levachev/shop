package com.example.shop.controller;

import com.example.shop.entity.User;
import com.example.shop.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepo repo;

    @GetMapping("/get")
    public String getAll(){
        List<User> userList = repo.findAll();
        String result = userList.stream().map(user -> user.getEmail()+" ").reduce("", String::concat);
        System.out.println(result);
        return result;
    }
}
