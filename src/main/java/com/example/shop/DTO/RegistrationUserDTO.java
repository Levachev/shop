package com.example.shop.DTO;

import com.example.shop.models.RoleEnum;

public record RegistrationUserDTO(
        String email,
        String firstname,
        String lastname,
        String password,
        RoleEnum role
) {
}
