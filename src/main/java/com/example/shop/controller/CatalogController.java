package com.example.shop.controller;

import com.example.shop.DTO.ProductDTO;
import com.example.shop.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    @GetMapping("/getAll")
    public List<ProductDTO> getAll(){
        return null;
    }
}
