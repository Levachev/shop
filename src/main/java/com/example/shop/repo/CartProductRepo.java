package com.example.shop.repo;

import com.example.shop.entity.CartProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CartProductRepo extends JpaRepository<CartProduct, Long> {
    @Query(value = "SELECT * FROM cart_product WHERE manufacturer_product_id = ?1 AND cart_id = ?2", nativeQuery = true)
    Optional<CartProduct> findByManufacturerProductAndCart(long manufacturerProductId, long cartId);

    @Query(value = "SELECT * FROM cart_product WHERE cart_id = ?1", nativeQuery = true)
    Page<CartProduct> findByCart(Pageable pageable, long cat_id);
}
