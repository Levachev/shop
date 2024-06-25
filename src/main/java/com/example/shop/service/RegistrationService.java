package com.example.shop.service;

import com.example.shop.entity.User;
import com.example.shop.models.ExistEnum;

public interface RegistrationService {
    ExistEnum registerUser(User user);
}
