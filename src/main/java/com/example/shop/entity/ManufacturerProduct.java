package com.example.shop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "manufacturer_product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ManufacturerProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int amount;

    private int price;

    private String description;

    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="product_id")
    private Product product;

    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;
}
