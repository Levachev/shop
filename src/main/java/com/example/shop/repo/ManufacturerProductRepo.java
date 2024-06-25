package com.example.shop.repo;

import com.example.shop.entity.ManufacturerProduct;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManufacturerProductRepo extends JpaRepository<ManufacturerProduct, Long>,
        JpaSpecificationExecutor<ManufacturerProduct> {

    @Query("SELECT manufacturer_product from ManufacturerProduct as manufacturer_product where manufacturer_product.manufacturer.id = :manufacturerId")
    List<ManufacturerProduct> findByManufacturerId(Long manufacturerId, Pageable pageable);

}
