package com.example.shop.service;

import com.example.shop.entity.ManufacturerProduct;
import com.example.shop.filter.ManufacturerProductFilter;
import com.example.shop.repo.ManufacturerProductRepo;
import com.example.shop.spec.ManufacturerProductSpec;
import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService {

    public static final String DEFAULT_SORT_PARAM = "price";

    @Autowired
    private ManufacturerProductRepo manufacturerProductRepo;


    public List<ManufacturerProduct> getAll(int page, ManufacturerProductFilter filter,
                                            String sortParam, boolean isDesc){
        int pageNumber = page < 1 ? 0 : page-1;
        int pageSize = 10;

        if(sortParam == null){
            sortParam = DEFAULT_SORT_PARAM;
        }
        Sort.Direction direction = isDesc ? Sort.Direction.DESC : Sort.Direction.ASC;

        Sort sort = Sort.by(direction, sortParam);

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Specification<ManufacturerProduct> specification = ManufacturerProductSpec.filterBy(filter);

        return manufacturerProductRepo.findAll(specification, pageable).getContent();
    }

    public boolean isSortParamValid(String param){
        //TODO
        return true;
    }

    public ManufacturerProduct getById(Long id){
        var tmp = manufacturerProductRepo.findById(id);

        return tmp.orElse(null);
    }
}
