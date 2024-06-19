package com.example.shop.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "manufacturer_product")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ManufacturerProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int amount;

    private int price;

    private String description;

    @ManyToOne (optional=false)
    @JoinColumn (name="product_id")
    private Product product;

    @ManyToOne (optional=false)
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;
}
