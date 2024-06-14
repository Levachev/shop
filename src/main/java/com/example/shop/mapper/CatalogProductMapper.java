package com.example.shop.mapper;

import com.example.shop.DTO.CatalogProductDTO;
import com.example.shop.entity.ManufacturerProduct;

public class CatalogProductMapper {
    public static CatalogProductDTO toCatalogProductDTO(ManufacturerProduct product){
        return new CatalogProductDTO(product.getId(), product.getProduct().getName(),
                product.getPrice(), product.getManufacturer().getName());
    }
}
