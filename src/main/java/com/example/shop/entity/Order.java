package com.example.shop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "\"order\"")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int amount;

    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn(name = "buyer_id")
    private User user;

    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;
}
