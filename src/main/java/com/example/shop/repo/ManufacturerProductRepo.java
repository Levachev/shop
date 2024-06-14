package com.example.shop.repo;

import com.example.shop.entity.ManufacturerProduct;
import org.springframework.data.repository.CrudRepository;

public interface ManufacturerProductRepo extends CrudRepository<ManufacturerProduct, Long> {
}
