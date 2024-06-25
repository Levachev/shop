package com.example.shop.DTO;

public record CartProductDTO(
        Long id,
        String name,
        String description,
        int price,
        int amount,
        String manufacturer
) {
}
