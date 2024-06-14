package com.example.shop.DTO;

public record RegistrationUserDTO(
        String email,
        String firstname,
        String lastname,
        String password
) {
}
