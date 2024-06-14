package com.example.shop.service;

import com.example.shop.models.SecurUserDetails;
import com.example.shop.entity.User;
import com.example.shop.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo repo;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> user = repo.findByEmail(email);
        return user.map(SecurUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("not found user - "+email));
    }
}
