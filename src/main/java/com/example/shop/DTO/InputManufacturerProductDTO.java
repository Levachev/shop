package com.example.shop.DTO;

public record InputManufacturerProductDTO(
        Long productId,
        String description,
        int price,
        int amount,
        Long manufacturerId
) {
}
