package com.example.shop.service;

import com.example.shop.DTO.InputManufacturerProductDTO;
import com.example.shop.DTO.UpdateManufacturerProductDTO;
import com.example.shop.entity.Manufacturer;
import com.example.shop.entity.ManufacturerProduct;
import com.example.shop.entity.Product;
import com.example.shop.filter.ManufacturerProductFilter;
import com.example.shop.models.SuccessEnum;
import com.example.shop.repo.ManufacturerProductRepo;
import com.example.shop.repo.ManufacturerRepo;
import com.example.shop.repo.ProductRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ManufacturerServiceImplTest {
    @Mock
    private ManufacturerProductRepo manufacturerProductRepo;
    @Mock
    private ManufacturerRepo manufacturerRepo;
    @Mock
    private ProductRepo productRepo;
    @InjectMocks
    private ManufacturerServiceImpl underTest;

    @Test
    void shouldShow() {
        //given
        List<ManufacturerProduct> productList = new ArrayList<>();
        int listSize = 3;
        for(int i=0;i<listSize;i++) {
            productList.add(
                    ManufacturerProduct.builder()
                            .build()
            );
        }
        //when

        when(manufacturerProductRepo.findByManufacturerId(anyLong(), any(Pageable.class))).thenReturn(productList);
        List<ManufacturerProduct> empList = underTest.show(0L, 1);
        //test

        assertEquals(listSize, empList.size());
        verify(manufacturerProductRepo).findByManufacturerId(anyLong(), any(Pageable.class));
    }

    @Test
    void shouldDelete() {
        //when
        underTest.delete(0L);

        //then
        verify(manufacturerProductRepo).deleteById(anyLong());
    }

    @Test
    void shouldGetById() {
        //given
        Optional<ManufacturerProduct> product = Optional.ofNullable(ManufacturerProduct.builder().build());

        //when
        when(manufacturerProductRepo.findById(anyLong())).thenReturn(product);
        var ret = underTest.getById(0L);

        //then
        assertEquals(product, ret);
        verify(manufacturerProductRepo).findById(anyLong());
    }

    @Test
    void shouldAddNewProduct() {
        //given
        Optional<Manufacturer> manufacturer = Optional.ofNullable(Manufacturer.builder().build());
        Optional<Product> product = Optional.ofNullable(Product.builder().build());

        //when
        when(manufacturerRepo.findById(anyLong())).thenReturn(manufacturer);
        when(productRepo.findById(anyLong())).thenReturn(product);
        var result = underTest.addNewProduct(
                new InputManufacturerProductDTO(0L, "", 1, 1, 0L));

        //then
        assertEquals(result, SuccessEnum.SUCCESS);
        verify(manufacturerProductRepo).save(any(ManufacturerProduct.class));
    }

    @Test
    void shouldNotAddNewProduct() {
        //given
        Optional<Manufacturer> manufacturer = Optional.ofNullable(Manufacturer.builder().build());

        //when
        when(manufacturerRepo.findById(anyLong())).thenReturn(manufacturer);
        when(productRepo.findById(anyLong())).thenReturn(Optional.empty());
        var result = underTest.addNewProduct(
                new InputManufacturerProductDTO(0L, "", 1, 1, 0L));

        //then
        assertEquals(result, SuccessEnum.FAIL);
    }

    @Test
    void shouldUpdateProduct() {
        //given
        Optional<ManufacturerProduct> product = Optional.ofNullable(ManufacturerProduct.builder().build());

        //when
        when(manufacturerProductRepo.findById(anyLong())).thenReturn(product);
        var result = underTest.updateProduct(0L,
                new UpdateManufacturerProductDTO( "", 1, 1));

        //then
        assertEquals(result, SuccessEnum.SUCCESS);
        verify(manufacturerProductRepo).save(any(ManufacturerProduct.class));
    }

    @Test
    void shouldNotUpdateProduct() {
        //when
        when(manufacturerProductRepo.findById(anyLong())).thenReturn(Optional.empty());
        var result = underTest.updateProduct(0L,
                new UpdateManufacturerProductDTO( "", 1, 1));

        //then
        assertEquals(result, SuccessEnum.FAIL);
    }
}