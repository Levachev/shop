package com.example.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(unique = true)
    private String email;
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @NotNull
    private String password;
    @NotNull
    private String role;

    @OneToMany(mappedBy="user", fetch= FetchType.LAZY)
    @JsonIgnore
    private List<Order> orderList;
}
