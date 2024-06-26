package com.example.shop.service;

import com.example.shop.entity.Cart;
import com.example.shop.entity.User;
import com.example.shop.repo.CartRepo;
import com.example.shop.repo.UserRepo;
import com.example.shop.models.ExistEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Qualifier("firstImpl")
public class RegistrationServiceImpl implements RegistrationService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ExistEnum registerUser(User user){
        Optional<User> tmp = userRepo.findByEmail(user.getEmail());

        if(tmp.isPresent()){
            return ExistEnum.EXIST;
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepo.save(user);

        Cart cart = Cart.builder()
                .user(user)
                .build();

        cartRepo.save(cart);

        return ExistEnum.NOT_EXIST;
    }
}
