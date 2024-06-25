package com.example.shop.service;

import com.example.shop.entity.ManufacturerProduct;
import org.springframework.data.domain.Page;

public interface AdminService {

    void deleteManufacturerProduct(Long id);

    Page<ManufacturerProduct> show(int page);
}
