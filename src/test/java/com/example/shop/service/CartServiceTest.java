package com.example.shop.service;

import com.example.shop.entity.Cart;
import com.example.shop.entity.CartProduct;
import com.example.shop.entity.ManufacturerProduct;
import com.example.shop.models.SuccessEnum;
import com.example.shop.repo.CartProductRepo;
import com.example.shop.repo.CartRepo;
import com.example.shop.repo.ManufacturerProductRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {
    @Mock
    private ManufacturerProductRepo manufacturerProductRepo;

    @Mock
    private CartProductRepo cartProductRepo;

    @Mock
    private CartRepo cartRepo;

    @InjectMocks
    private CartService underTest;


    @Test
    void shouldAddToCart() {
        //given
        ManufacturerProduct manufacturerProduct = ManufacturerProduct.builder()
                .id(0L)
                .amount(10000)
                .build();
        Cart cart = Cart.builder().id(0L).build();

        //when
        when(manufacturerProductRepo.findById(anyLong())).thenReturn(Optional.ofNullable(manufacturerProduct));
        when(cartRepo.findById(anyLong())).thenReturn(Optional.ofNullable(cart));
        SuccessEnum result = underTest.addToCart(0L, 0L, 3);

        //then
        assertEquals(result, SuccessEnum.SUCCESS);
        verify(manufacturerProductRepo).findById(anyLong());
        verify(cartRepo).findById(anyLong());
        verify(cartProductRepo).findByManufacturerProductAndCart(anyLong(), anyLong());
        verify(manufacturerProductRepo).save(any(ManufacturerProduct.class));
        verify(cartProductRepo).save(any(CartProduct.class));
    }

    @Test
    void shouldNotAddToCartNotEnoughAmount() {
        //given
        ManufacturerProduct manufacturerProduct = ManufacturerProduct.builder()
                .id(0L)
                .amount(0)
                .build();
        Cart cart = Cart.builder().id(0L).build();

        //when
        when(manufacturerProductRepo.findById(anyLong())).thenReturn(Optional.ofNullable(manufacturerProduct));
        when(cartRepo.findById(anyLong())).thenReturn(Optional.ofNullable(cart));
        SuccessEnum result = underTest.addToCart(0L, 0L, 3);

        //then
        assertEquals(result, SuccessEnum.FAIL);

    }

    @Test
    void shouldNotAddToCartNoSupportObject() {
        //given
        Cart cart = Cart.builder().id(0L).build();

        //when
        when(manufacturerProductRepo.findById(anyLong())).thenReturn(Optional.empty());
        when(cartRepo.findById(anyLong())).thenReturn(Optional.ofNullable(cart));
        SuccessEnum result = underTest.addToCart(0L, 0L, 3);

        //then
        assertEquals(result, SuccessEnum.FAIL);

    }

    @Test
    void shouldDeleteFromCart() {
        //given
        ManufacturerProduct manufacturerProduct = ManufacturerProduct.builder()
                .id(0L)
                .amount(10000)
                .build();
        Cart cart = Cart.builder().id(0L).build();

        CartProduct cartProduct = CartProduct.builder()
                .manufacturerProduct(manufacturerProduct)
                .cart(cart)
                .amount(6)
                .build();

        //when
        when(manufacturerProductRepo.findById(anyLong())).thenReturn(Optional.ofNullable(manufacturerProduct));
        when(cartRepo.findById(anyLong())).thenReturn(Optional.ofNullable(cart));
        when(cartProductRepo.findByManufacturerProductAndCart(anyLong(), anyLong())).thenReturn(Optional.ofNullable(cartProduct));
        SuccessEnum result = underTest.deleteFromCart(0L, 0L, 3);

        //then
        assertEquals(result, SuccessEnum.SUCCESS);
        verify(manufacturerProductRepo).findById(anyLong());
        verify(cartRepo).findById(anyLong());
        verify(cartProductRepo).findByManufacturerProductAndCart(anyLong(), anyLong());
        verify(manufacturerProductRepo).save(any(ManufacturerProduct.class));
        verify(cartProductRepo).save(any(CartProduct.class));
    }

    @Test
    void shouldTotalDeleteFromCart() {
        //given
        ManufacturerProduct manufacturerProduct = ManufacturerProduct.builder()
                .id(0L)
                .amount(10000)
                .build();
        Cart cart = Cart.builder().id(0L).build();

        CartProduct cartProduct = CartProduct.builder()
                .manufacturerProduct(manufacturerProduct)
                .cart(cart)
                .amount(6)
                .build();

        //when
        when(manufacturerProductRepo.findById(anyLong())).thenReturn(Optional.ofNullable(manufacturerProduct));
        when(cartRepo.findById(anyLong())).thenReturn(Optional.ofNullable(cart));
        when(cartProductRepo.findByManufacturerProductAndCart(anyLong(), anyLong())).thenReturn(Optional.ofNullable(cartProduct));
        SuccessEnum result = underTest.deleteFromCart(0L, 0L, 6);

        //then
        assertEquals(result, SuccessEnum.SUCCESS);
        verify(manufacturerProductRepo).findById(anyLong());
        verify(cartRepo).findById(anyLong());
        verify(cartProductRepo).findByManufacturerProductAndCart(anyLong(), anyLong());
        verify(manufacturerProductRepo).save(any(ManufacturerProduct.class));
        verify(cartProductRepo).delete(any(CartProduct.class));
    }

    @Test
    void shouldNotDeleteFromCartNoCartProduct() {
        //given
        ManufacturerProduct manufacturerProduct = ManufacturerProduct.builder()
                .id(0L)
                .amount(10000)
                .build();
        Cart cart = Cart.builder().id(0L).build();

        CartProduct cartProduct = CartProduct.builder()
                .manufacturerProduct(manufacturerProduct)
                .cart(cart)
                .amount(6)
                .build();

        //when
        when(manufacturerProductRepo.findById(anyLong())).thenReturn(Optional.ofNullable(manufacturerProduct));
        when(cartRepo.findById(anyLong())).thenReturn(Optional.ofNullable(cart));
        when(cartProductRepo.findByManufacturerProductAndCart(anyLong(), anyLong())).thenReturn(Optional.empty());
        SuccessEnum result = underTest.deleteFromCart(0L, 0L, 3);

        //then
        assertEquals(result, SuccessEnum.FAIL);
    }

    @Test
    void shouldNotDeleteFromCartNoSupportObject() {
        //given
        Cart cart = Cart.builder().id(0L).build();


        //when
        when(manufacturerProductRepo.findById(anyLong())).thenReturn(Optional.empty());
        when(cartRepo.findById(anyLong())).thenReturn(Optional.ofNullable(cart));
        SuccessEnum result = underTest.deleteFromCart(0L, 0L, 3);

        //then
        assertEquals(result, SuccessEnum.FAIL);
    }

    @Test
    void shouldOrder() {
        //given
        Cart cart = Cart.builder()
                .id(0L)
                .cartProducts(new ArrayList<>())
                .build();
        int cartProductSize = 5;
        for(int i=0;i<cartProductSize;i++){
            cart.getCartProducts().add(
                    CartProduct.builder().build()
            );
        }
        //when
        when(cartRepo.findById(anyLong())).thenReturn(Optional.of(cart));
        SuccessEnum result = underTest.order(0L);

        //then
        assertEquals(result, SuccessEnum.SUCCESS);
        verify(cartProductRepo, times(cartProductSize)).delete(any(CartProduct.class));
    }

    @Test
    void shouldNotOrder() {
        //given

        //when
        when(cartRepo.findById(anyLong())).thenReturn(Optional.empty());
        SuccessEnum result = underTest.order(0L);

        //then
        assertEquals(result, SuccessEnum.FAIL);
    }

    @Test
    void shouldShow() {
        //given
        List<CartProduct> cartProducts = new ArrayList<>();

        int cartProductsSize = 9;
        for(int i=0;i<cartProductsSize;i++){
            cartProducts.add(
                    CartProduct.builder()
                            .id((long) i)
                            .build()
            );
        }

        //when
        when(cartProductRepo.findByCart(any(Pageable.class), anyLong())).thenReturn(new PageImpl<>(cartProducts));
        List<CartProduct> resultList = underTest.show(0L, 1);

        //then
        assertEquals(cartProductsSize, resultList.size());
        verify(cartProductRepo).findByCart(any(Pageable.class), anyLong());
    }
}