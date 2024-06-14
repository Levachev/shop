package com.example.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "cart")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Cart {
    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    @OneToMany(mappedBy="cart", fetch= FetchType.LAZY)
    @JsonIgnore
    private List<CartProduct> cartProducts;
}
