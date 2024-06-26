package com.example.shop.service;

import com.example.shop.entity.ManufacturerProduct;
import com.example.shop.repo.ManufacturerProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private ManufacturerProductRepo manufacturerProductRepo;

    @Override
    public void deleteManufacturerProduct(Long id){
        manufacturerProductRepo.deleteById(id);
    }

    @Override
    public List<ManufacturerProduct> show(int page){
        int pageNumber = page < 1 ? 0 : page-1;
        int pageSize = 10;

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return manufacturerProductRepo.findAll(pageable).getContent();
    }
}
