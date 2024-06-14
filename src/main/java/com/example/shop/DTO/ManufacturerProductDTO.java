package com.example.shop.DTO;

public record ManufacturerProductDTO(
        Long id,
        String name,
        String description,
        int price,
        int amount,
        String manufacturer
) {
}
