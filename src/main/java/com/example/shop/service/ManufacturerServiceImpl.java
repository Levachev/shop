package com.example.shop.service;

import com.example.shop.DTO.InputManufacturerProductDTO;
import com.example.shop.DTO.UpdateManufacturerProductDTO;
import com.example.shop.entity.Manufacturer;
import com.example.shop.entity.ManufacturerProduct;
import com.example.shop.entity.Product;
import com.example.shop.models.SuccessEnum;
import com.example.shop.repo.ManufacturerProductRepo;
import com.example.shop.repo.ManufacturerRepo;
import com.example.shop.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Qualifier("firstImpl")
public class ManufacturerServiceImpl implements ManufacturerService {
    @Autowired
    private ManufacturerProductRepo manufacturerProductRepo;

    @Autowired
    private ManufacturerRepo manufacturerRepo;

    @Autowired
    private ProductRepo productRepo;

    @Override
    public List<ManufacturerProduct> show(Long manufacturerId, int page){
        int pageNumber = page < 1 ? 0 : page-1;
        int pageSize = 10;

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return manufacturerProductRepo.findByManufacturerId(manufacturerId, pageable);
    }

    @Override
    public void delete(Long id){
        manufacturerProductRepo.deleteById(id);
    }

    @Override
    public Optional<ManufacturerProduct> getById(Long id){
        return manufacturerProductRepo.findById(id);
    }

    @Override
    @Transactional
    public SuccessEnum addNewProduct(InputManufacturerProductDTO productDTO){
        Optional<Manufacturer> manufacturerOptional
                = manufacturerRepo.findById(productDTO.manufacturerId());
        Optional<Product> productOptional = productRepo.findById(productDTO.productId());

        if(manufacturerOptional.isEmpty() || productOptional.isEmpty()){
            return SuccessEnum.FAIL;
        }

        manufacturerProductRepo.save(
                ManufacturerProduct.builder()
                        .product(productOptional.get())
                        .manufacturer(manufacturerOptional.get())
                        .price(productDTO.price())
                        .amount(productDTO.amount())
                        .description(productDTO.description())
                        .build()
        );

        return SuccessEnum.SUCCESS;
    }

    @Override
    @Transactional
    public SuccessEnum updateProduct(Long id, UpdateManufacturerProductDTO productDTO){
        Optional<ManufacturerProduct> manufacturerProductOptional = manufacturerProductRepo.findById(id);

        if(manufacturerProductOptional.isEmpty()){
            return SuccessEnum.FAIL;
        }
        ManufacturerProduct manufacturerProduct = manufacturerProductOptional.get();
        manufacturerProduct.setAmount(productDTO.amount());
        manufacturerProduct.setPrice(productDTO.price());
        manufacturerProduct.setDescription(productDTO.description());

        manufacturerProductRepo.save(manufacturerProduct);

        return SuccessEnum.SUCCESS;
    }
}
