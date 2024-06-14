package com.example.shop.mapper;

import com.example.shop.DTO.CatalogProductDTO;
import com.example.shop.DTO.ManufacturerProductDTO;
import com.example.shop.entity.ManufacturerProduct;

public class ManufacturerProductMapper {
    public static ManufacturerProductDTO toCatalogProductDTO(ManufacturerProduct product){
        if(product == null){
            return null;
        }
        return new ManufacturerProductDTO(product.getId(), product.getProduct().getName(),
                product.getDescription(), product.getPrice(),
                product.getAmount(), product.getManufacturer().getName());
    }
}
