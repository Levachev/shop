package com.example.shop.controller;

import com.example.shop.DTO.CatalogProductDTO;
import com.example.shop.filter.ManufacturerProductFilter;
import com.example.shop.mapper.CatalogProductMapper;
import com.example.shop.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Qualifier("firstImpl")
    private CatalogService catalogService;

    @GetMapping("/getAll")
    public List<CatalogProductDTO> getAll(@RequestParam(required = false, defaultValue = "0") int page,
                                          @RequestParam(required = false, value = "product_name") String productName,
                                          @RequestParam(required = false, value = "manufacturer_name") String manufacturerName,
                                          @RequestParam(required = false, value = "price_to") Integer priceTo,
                                          @RequestParam(required = false, value = "price_from") Integer priceFrom,
                                          @RequestParam(required = false, value = "sort_param") String sortParam,
                                          @RequestParam(required = false, value = "is_desc", defaultValue = "true") boolean isDesc){
        ManufacturerProductFilter filter =
                new ManufacturerProductFilter(priceFrom, priceTo, productName,manufacturerName);

        if(!catalogService.isSortParamValid(sortParam)){
            return null;
        }

        return catalogService.getAll(page, filter, sortParam, isDesc).stream()
                .map(CatalogProductMapper::toCatalogProductDTO)
                .collect(Collectors.toList());
    }
}
