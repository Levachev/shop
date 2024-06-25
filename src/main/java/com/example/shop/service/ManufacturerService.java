package com.example.shop.service;

import com.example.shop.DTO.InputManufacturerProductDTO;
import com.example.shop.DTO.UpdateManufacturerProductDTO;
import com.example.shop.entity.ManufacturerProduct;
import com.example.shop.models.SuccessEnum;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ManufacturerService {
    List<ManufacturerProduct> show(Long manufacturerId, int page);

    void delete(Long id);

    Optional<ManufacturerProduct> getById(Long id);

    @Transactional
    SuccessEnum addNewProduct(InputManufacturerProductDTO productDTO);

    @Transactional
    SuccessEnum updateProduct(Long id, UpdateManufacturerProductDTO productDTO);
}
