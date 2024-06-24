package com.example.shop.repo;

import com.example.shop.entity.ManufacturerProduct;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerProductRepo extends JpaRepository<ManufacturerProduct, Long>,
        JpaSpecificationExecutor<ManufacturerProduct> {

//    @Query(value = "SELECT DISTINCT COL_NAME FROM myTable WHERE UPPER(COL_NAME) LIKE UPPER('%PriceOrder%')", nativeQuery = true)
//    Page<ManufacturerProduct> findAll(@Nullable Specification<ManufacturerProduct> spec,
//                                      @NonNull Pageable pageable, String searchStr);


}
