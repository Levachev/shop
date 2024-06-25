package com.example.shop.service;

import com.example.shop.entity.ManufacturerProduct;
import com.example.shop.filter.ManufacturerProductFilter;

import java.util.List;

public interface CatalogService {
    List<ManufacturerProduct> getAll(int page, ManufacturerProductFilter filter,
                                     String sortParam, boolean isDesc);

    boolean isSortParamValid(String param);

    ManufacturerProduct getById(Long id);
}
