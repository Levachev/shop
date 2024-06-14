package com.example.shop.service;

import com.example.shop.entity.User;
import com.example.shop.repo.UserRepo;
import com.example.shop.models.ExistEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ExistEnum registerUser(User user){
        Optional<User> tmp = userRepo.findByEmail(user.getEmail());

        if(tmp.isEmpty()){
            return ExistEnum.EXIST;
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        userRepo.save(user);

        return ExistEnum.NOT_EXIST;
    }
}
