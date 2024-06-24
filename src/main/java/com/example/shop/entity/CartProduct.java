package com.example.shop.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cart_product",
uniqueConstraints = @UniqueConstraint(columnNames = {"manufacturer_product_id", "cart_id"}))
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int amount;

    @ManyToOne (optional=false, cascade=CascadeType.MERGE)
    @JoinColumn(name = "manufacturer_product_id")
    private ManufacturerProduct manufacturerProduct;

    @ManyToOne (optional=false, cascade=CascadeType.MERGE)
    @JoinColumn(name = "cart_id")
    private Cart cart;
}
