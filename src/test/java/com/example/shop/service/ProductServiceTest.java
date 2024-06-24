package com.example.shop.service;

import com.example.shop.entity.ManufacturerProduct;
import com.example.shop.repo.ManufacturerProductRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private ManufacturerProductRepo manufacturerProductRepo;
    @InjectMocks
    private ProductService underTest;

    @Test
    void shouldGetById() {
        //given
        ManufacturerProduct product =
                ManufacturerProduct.builder()
                        .id(0L)
                        .build();

        //when
        when(manufacturerProductRepo.findById(anyLong())).thenReturn(Optional.ofNullable(product));
        ManufacturerProduct getProduct = underTest.getById(0L);

        //then

        assertEquals(getProduct, product);
        verify(manufacturerProductRepo).findById(
                ArgumentCaptor.forClass(Long.class).capture()
        );
    }

}