package com.example.shop;

import com.example.shop.entity.User;
import com.example.shop.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class StartupHousekeeper implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserRepo userRepo;

    @Autowired
    public StartupHousekeeper(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        userRepo.deleteAll();

        userRepo.save(User.builder()
                .role("ROLE_USER")
                .email("user1@gmail.com")
                .firstname("roman")
                .lastname("romanov")
                .password(passwordEncoder.encode("12345"))
                .build());

        userRepo.save(User.builder()
                .role("ROLE_ADMIN")
                .email("admin@gmail.com")
                .firstname("admin")
                .lastname("admin")
                .password(passwordEncoder.encode("admin"))
                .build());
    }
}
