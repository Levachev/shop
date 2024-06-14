package com.example.shop.controller;

import com.example.shop.DTO.RegistrationUserDTO;
import com.example.shop.service.RegistrationService;
import com.example.shop.models.ExistEnum;
import com.example.shop.mapper.RegistrationUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private RegistrationService service;

    @GetMapping
    public String registerUser(@RequestParam RegistrationUserDTO userDTO){

        ExistEnum retVal = service.registerUser(RegistrationUserMapper.toUser(userDTO));

        if(retVal == ExistEnum.NOT_EXIST){
            return "OK";
        }

        return "this user already exist";

    }
}
