package com.example.shop.mapper;

import com.example.shop.DTO.CartProductDTO;
import com.example.shop.entity.CartProduct;

public class CartProductMapper {
    public static CartProductDTO toCartProductDTO(CartProduct product){
        if(product == null){
            return null;
        }
        return new CartProductDTO(product.getId(), product.getManufacturerProduct().getProduct().getName(),
                product.getManufacturerProduct().getDescription(), product.getManufacturerProduct().getPrice(),
                product.getAmount(), product.getManufacturerProduct().getManufacturer().getName());
    }
}
