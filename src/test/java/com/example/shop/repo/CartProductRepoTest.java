package com.example.shop.repo;

import com.example.shop.entity.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
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
    @Autowired
    private ProductRepo productRepo;

    private ManufacturerProduct manufacturerProduct;
    private Cart cart;

    @BeforeEach
    public void setup() {
        Product product = Product.builder().name("product").build();
        productRepo.save(product);

        Manufacturer manufacturer = Manufacturer.builder().name("manufacturer").build();
        manufacturerRepo.save(manufacturer);
        manufacturerProduct = ManufacturerProduct.builder()
                .description("description")
                .price(11)
                .amount(11)
                .product(product)
                .manufacturer(manufacturer)
                .build();
        manufacturerProduct = manufacturerProductRepo.save(manufacturerProduct);

        User user = User.builder()
                .role("role")
                .firstname("fname")
                .lastname("lname")
                .password("password")
                .email("email")
                .build();

        cart = Cart.builder()
                .user(user)
                .build();
        cart = cartRepo.save(cart);
        //userRepo.save(user);
    }

    @AfterEach
    public void teardown() {
        underTest.deleteAll();
        manufacturerProductRepo.deleteAll();
        manufacturerRepo.deleteAll();
        productRepo.deleteAll();
        cartRepo.deleteAll();
        userRepo.deleteAll();
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
        List<CartProduct> products = underTest.findByCart(PageRequest.of(0, 1), cart.getId())
                .getContent();
        //then
        List<CartProduct> sortedProducts = products.stream().filter(
                tmp -> (Objects.equals(tmp.getId(), savedCartProduct.getId()))
        ).toList();

        assertFalse(sortedProducts.isEmpty());
        assertEquals(sortedProducts.size(), 1);
    }
}