package com.example.shop.repo;

import com.example.shop.entity.ManufacturerProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturerProductRepo extends JpaRepository<ManufacturerProduct, Long> {
}
