package com.example.shop.filter;


public record ManufacturerProductFilter(
        Integer priceFrom,
        Integer priceTo,
        String productName,
        String manufacturerName
) {
}
