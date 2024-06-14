package com.example.shop.repo;

import com.example.shop.entity.Manufacturer;
import org.springframework.data.repository.CrudRepository;

public interface ManufacturerRepo extends CrudRepository<Manufacturer, Long> {
}
