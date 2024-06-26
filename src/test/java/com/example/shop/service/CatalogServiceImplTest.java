package com.example.shop.service;

import com.example.shop.entity.ManufacturerProduct;
import com.example.shop.filter.ManufacturerProductFilter;
import com.example.shop.repo.ManufacturerProductRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CatalogServiceImplTest {
    @Mock
    private ManufacturerProductRepo manufacturerProductRepo;
    @InjectMocks
    private CatalogServiceImpl underTest;

//    @BeforeEach
//    public void init() {
//        MockitoAnnotations.openMocks(this);
//    }

    @Test
    void shouldGetAll() {
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

        when(manufacturerProductRepo.findAll(any(Specification.class), any(Pageable.class))).thenReturn(new PageImpl<>(productList));
        List<ManufacturerProduct> empList = underTest.getAll(0,
                new ManufacturerProductFilter(null, null, null, null),
                null, true);
        //test


        assertEquals(listSize, empList.size());
        verify(manufacturerProductRepo).findAll(any(Specification.class), any(Pageable.class));

    }

    @Test
    void shouldGetById() {
        //given
        Long id = 0L;
        //when
        underTest.getById(id);
        //then
        ArgumentCaptor<Long> idArgumentCapture = ArgumentCaptor.forClass(Long.class);
        verify(manufacturerProductRepo).findById(idArgumentCapture.capture());
    }
}