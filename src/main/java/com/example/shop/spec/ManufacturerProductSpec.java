package com.example.shop.spec;

import com.example.shop.entity.Manufacturer;
import com.example.shop.entity.ManufacturerProduct;
import com.example.shop.entity.Product;
import com.example.shop.filter.ManufacturerProductFilter;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class ManufacturerProductSpec {
    private static final String PRODUCT_NAME = "name";
    private static final String MANUFACTURER_NAME = "name";
    private static final String PRICE = "price";

    private ManufacturerProductSpec() {
    }

    public static Specification<ManufacturerProduct> filterBy(ManufacturerProductFilter filter) {
        return Specification
                .where(hasProductName(filter.productName()))
                .and(hasManufacturerName(filter.manufacturerName()))
                .and(hasPriceGreaterThan(filter.priceFrom()))
                .and(hasPriceLessThan(filter.priceTo()));
    }

    private static Specification<ManufacturerProduct> hasProductName(String productName) {
        return ((root, query, cb)
                -> {
            Join<ManufacturerProduct, Product> products = root.join("product");
            return productName == null || productName.isEmpty() ?
                    cb.conjunction() :
                    //cb.equal(authorsBook.get(PRODUCT_NAME), productName);
                    cb.like(cb.upper(products.get(PRODUCT_NAME)),
                            "%"+productName.toUpperCase()+"%");
        });
    }

    private static Specification<ManufacturerProduct> hasManufacturerName(String manufacturerName) {
        return ((root, query, cb) -> {
            Join<ManufacturerProduct, Manufacturer> products = root.join("manufacturer");
            return manufacturerName == null || manufacturerName.isEmpty() ?
                    cb.conjunction() :
                    //cb.equal(authorsBook.get(PRODUCT_NAME), productName);
                    cb.like(cb.upper(products.get(MANUFACTURER_NAME)),
                            "%"+manufacturerName.toUpperCase()+"%");
        });
    }

    private static Specification<ManufacturerProduct> hasPriceGreaterThan(Integer priceFrom) {
        return (root, query, cb) -> priceFrom == null ? cb.conjunction() : cb.greaterThan(root.get(PRICE), priceFrom);
    }

    private static Specification<ManufacturerProduct> hasPriceLessThan(Integer priceTo) {
        return (root, query, cb) -> priceTo == null ? cb.conjunction() : cb.lessThan(root.get(PRICE), priceTo);
    }

}
