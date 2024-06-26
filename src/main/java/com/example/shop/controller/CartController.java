package com.example.shop.controller;

import com.example.shop.DTO.CartProductDTO;
import com.example.shop.DTO.CatalogProductDTO;
import com.example.shop.mapper.CartProductMapper;
import com.example.shop.mapper.CatalogProductMapper;
import com.example.shop.service.CartService;
import com.example.shop.service.CatalogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    @Qualifier("firstImpl")
    private CartService cartService;


    @GetMapping("/show")
    public List<CartProductDTO> showCart(@RequestParam("user_id") Long userId, @RequestParam int page){
        return cartService.show(userId, page).stream()
                .map(CartProductMapper::toCartProductDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/add")
    public void addToCart(@RequestParam("user_id") Long userId,
                          @RequestParam("product_id") Long productId,
                          @RequestParam int amount){
        cartService.addToCart(userId, productId, amount);
    }

    @GetMapping("/delete")
    public void deleteToCart(@RequestParam("user_id") Long userId,
                             @RequestParam("product_id") Long productId,
                             @RequestParam int amount){
        cartService.deleteFromCart(userId, productId, amount);
    }

    @GetMapping("/order")
    public void order(@RequestParam("user_id") Long userId){
        cartService.order(userId);
    }
}
