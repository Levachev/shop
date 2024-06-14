package com.example.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "manufacturer")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy="manufacturer_product", fetch= FetchType.LAZY)
    @JsonIgnore
    private List<ManufacturerProduct> manufacturerProductList;
}
