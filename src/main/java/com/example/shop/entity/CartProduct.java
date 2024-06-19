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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int amount;

    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn(name = "manufacturer_product_id")
    private ManufacturerProduct manufacturerProduct;

    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private Cart cart;
}
