package com.example.shop.controller;

import com.example.shop.DTO.CatalogProductDTO;
import com.example.shop.DTO.ManufacturerProductDTO;
import com.example.shop.mapper.CatalogProductMapper;
import com.example.shop.mapper.ManufacturerProductMapper;
import com.example.shop.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    @GetMapping("/getAll")
    public List<CatalogProductDTO> getAll(@RequestParam int page){
        return catalogService.getAll(page).stream().map(CatalogProductMapper::toCatalogProductDTO).collect(Collectors.toList());
    }
}
