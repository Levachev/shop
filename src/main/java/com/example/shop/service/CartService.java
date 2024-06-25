package com.example.shop.service;

import com.example.shop.entity.CartProduct;
import com.example.shop.models.SuccessEnum;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartService {
    @Transactional
    SuccessEnum addToCart(Long userId, Long productId, int amount);

    @Transactional
    SuccessEnum deleteFromCart(Long userId, Long productId, int amount);

    @Transactional
    SuccessEnum order(Long userId);

    List<CartProduct> show(Long userId, int page);
}
