package com.example.shop.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
    @EmbeddedId
    private ManufacturerProductId id;
    private int amount;
}
