package com.example.shop.controller;

import com.example.shop.service.CartService;
import com.example.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;


    @GetMapping("/show")
    public void showCart(@RequestParam("user_id") Long userId, @RequestParam int page){
        cartService.show(userId, page);
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
        cartService.deleteToCart(userId, productId, amount);
    }

    @GetMapping("/order")
    public void order(@RequestParam("user_id") Long userId){
        cartService.order(userId);
    }
}
