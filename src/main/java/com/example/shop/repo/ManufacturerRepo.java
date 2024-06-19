package com.example.shop.repo;

import com.example.shop.entity.Manufacturer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepo extends CrudRepository<Manufacturer, Long> {
}
