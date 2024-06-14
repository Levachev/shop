package com.example.shop.util;

import com.example.shop.DTO.RegistrationUserDTO;
import com.example.shop.entity.User;

public class RegistrationUserMapper {

    public static User toUser(RegistrationUserDTO registrationUserDTO){
        return User.builder()
                .email(registrationUserDTO.email())
                .password(registrationUserDTO.password())
                .firstname(registrationUserDTO.firstname())
                .lastname(registrationUserDTO.lastname())
                .build();
    }

}
