package com.example.shop.service;

import com.example.shop.entity.ManufacturerProduct;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AdminService {

    void deleteManufacturerProduct(Long id);

    List<ManufacturerProduct> show(int page);
}
