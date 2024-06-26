package com.example.shop.repo;

import com.example.shop.entity.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ManufacturerProductRepoTest {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ManufacturerRepo manufacturerRepo;
    @Autowired
    private ManufacturerProductRepo underTest;
    private Manufacturer manufacturer;
    private Product product;


    @BeforeEach
    public void setup() {
        product = Product.builder().name("product").build();
        product = productRepo.save(product);

        manufacturer = Manufacturer.builder().name("manufacturer").build();
        manufacturer = manufacturerRepo.save(manufacturer);
    }

    @AfterEach
    public void teardown() {
        underTest.deleteAll();
        manufacturerRepo.deleteAll();
        productRepo.deleteAll();
    }


    @Test
    void findByManufacturerId() {
        //given
        ManufacturerProduct manufacturerProduct = ManufacturerProduct.builder()
                .description("")
                .price(1)
                .amount(2)
                .manufacturer(manufacturer)
                .product(product)
                .build();
        ManufacturerProduct savedProduct = underTest.save(manufacturerProduct);

        //when
        List<ManufacturerProduct> result = underTest.findByManufacturerId(manufacturer.getId(),
                PageRequest.of(0, 1));

        //then
        List<ManufacturerProduct> sortedProducts = result.stream().filter(
                tmp -> (Objects.equals(tmp.getId(), savedProduct.getId()))
        ).toList();

        assertFalse(sortedProducts.isEmpty());
        assertEquals(sortedProducts.size(), 1);
    }
}