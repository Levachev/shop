package com.example.shop.repo;

import com.example.shop.entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CartProductRepo extends JpaRepository<CartProduct, Long> {
    @Query(value = "SELECT * FROM cart_product WHERE manufacturer_product_id = ?1", nativeQuery = true)
    List<CartProduct> findByManufacturerProduct(long manufacturer_product_id);
}
