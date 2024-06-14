package com.example.shop.controller;

import com.example.shop.DTO.ManufacturerProductDTO;
import com.example.shop.mapper.ManufacturerProductMapper;
import com.example.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;


    @GetMapping("/get")
    public ManufacturerProductDTO get(@RequestParam Long id){
        return ManufacturerProductMapper.toCatalogProductDTO(productService.getById(id));
    }

    @GetMapping("/cart/add")
    public void addToCart(@RequestParam("user_id") Long userId,
                          @RequestParam("product_id") Long productId,
                          @RequestParam int amount){
        productService.addToCart(userId, productId, amount);
    }

    @GetMapping("/cart/delete")
    public void deleteToCart(@RequestParam("user_id") Long userId,
                          @RequestParam("product_id") Long productId,
                             @RequestParam int amount){
        productService.deleteToCart(userId, productId, amount);
    }
}
