package com.example.shop.service;

import com.example.shop.entity.ManufacturerProduct;
import com.example.shop.repo.ManufacturerProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ManufacturerProductRepo manufacturerProductRepo;


    @Override
    public ManufacturerProduct getById(Long id){
        var tmp = manufacturerProductRepo.findById(id);

        return tmp.orElse(null);
    }
}
