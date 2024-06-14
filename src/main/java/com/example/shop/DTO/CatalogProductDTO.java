package com.example.shop.DTO;

public record CatalogProductDTO(
        Long id,
        String name,
        int price,
        String manufacturer
) {
}
