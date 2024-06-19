package com.example.shop.repo;

import com.example.shop.entity.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CartProductRepoTest {
    @Autowired
    private CartProductRepo underTest;
    @Autowired
    private ManufacturerProductRepo manufacturerProductRepo;
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private ManufacturerRepo manufacturerRepo;
    @Autowired
    private UserRepo userRepo;

    private ManufacturerProduct manufacturerProduct;
    private Cart cart;

    @BeforeAll
    public void setup() {
        manufacturerProduct = ManufacturerProduct.builder()
                .description("description")
                .price(11)
                .amount(11)
                .manufacturer(
                        manufacturerRepo.save(
                            Manufacturer.builder().name("manufacturer").build()
                        )
                )
                .build();
        manufacturerProduct = manufacturerProductRepo.save(manufacturerProduct);

        cart = Cart.builder()
                .user(
                    userRepo.save(
                        User.builder()
                        .role("role")
                        .firstname("fname")
                        .lastname("lname")
                        .password("password")
                        .email("email")
                        .build()
                    )
                )
                .build();
        cart = cartRepo.save(cart);
    }

    @AfterAll
    public void teardown() {
        userRepo.deleteAll();
        cartRepo.deleteAll();
        manufacturerRepo.deleteAll();
        manufacturerProductRepo.deleteAll();
        underTest.deleteAll();
    }

    @Test
    void shouldFindByManufacturerProductAndCart() {
        //given
        CartProduct cartProduct = CartProduct.builder()
                .manufacturerProduct(manufacturerProduct)
                .cart(cart)
                .amount(1)
                .build();
        underTest.save(cartProduct);
        //when
        Optional<CartProduct> result = underTest
                .findByManufacturerProductAndCart(manufacturerProduct.getId(), cart.getId());
        //then
        assertTrue(result.isPresent());
        CartProduct resultCartProduct = result.get();
        assertEquals(cartProduct.getCart().getId(), resultCartProduct.getCart().getId());
        assertEquals(cartProduct.getManufacturerProduct().getId(),
                resultCartProduct.getManufacturerProduct().getId());
    }

    @Test
    void findByCart() {
        //given
        CartProduct cartProduct = CartProduct.builder()
                .manufacturerProduct(manufacturerProduct)
                .cart(cart)
                .amount(1)
                .build();
        CartProduct savedCartProduct = underTest.save(cartProduct);
        //when
        List<CartProduct> products = underTest.findByCart(PageRequest.of(1, 1), cart.getId())
                .getContent();
        //then
        List<CartProduct> sortedProducts = products.stream().filter(
                tmp -> (Objects.equals(tmp.getId(), cartProduct.getId()))
        ).toList();

        assertFalse(sortedProducts.isEmpty());
        assertEquals(sortedProducts.getFirst().getId(), savedCartProduct.getId());
    }
}