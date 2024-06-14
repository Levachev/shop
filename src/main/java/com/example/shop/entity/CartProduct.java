package com.example.shop.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cart_product")
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
    @JoinColumn(name = "manufacturer_product_id", unique = true)
    private ManufacturerProduct manufacturerProduct;

    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private Cart cart;
}
