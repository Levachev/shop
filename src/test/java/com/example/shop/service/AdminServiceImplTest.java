package com.example.shop.service;

import com.example.shop.entity.ManufacturerProduct;
import com.example.shop.repo.ManufacturerProductRepo;
import com.example.shop.repo.ManufacturerRepo;
import com.example.shop.repo.ProductRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdminServiceImplTest {
    @Mock
    private ManufacturerProductRepo manufacturerProductRepo;
    @InjectMocks
    private AdminServiceImpl underTest;

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

        when(manufacturerProductRepo.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(productList));
        List<ManufacturerProduct> empList = underTest.show(1);
        //test

        assertEquals(listSize, empList.size());
        verify(manufacturerProductRepo).findAll(any(Pageable.class));
    }

    @Test
    void shouldDelete() {
        //when
        underTest.deleteManufacturerProduct(0L);

        //then
        verify(manufacturerProductRepo).deleteById(anyLong());
    }
}