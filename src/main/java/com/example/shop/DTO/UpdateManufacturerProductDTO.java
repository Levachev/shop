package com.example.shop.DTO;

public record UpdateManufacturerProductDTO(
        String description,
        int price,
        int amount
) {
}
