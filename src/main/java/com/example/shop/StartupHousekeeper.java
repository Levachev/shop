package com.example.shop;

import com.example.shop.entity.*;
import com.example.shop.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class StartupHousekeeper implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserRepo userRepo;
    private final CartRepo cartRepo;
    private final ManufacturerProductRepo manufacturerProductRepo;
    private final ManufacturerRepo manufacturerRepo;
    private final ProductRepo productRepo;

    @Autowired
    public StartupHousekeeper(UserRepo userRepo, ManufacturerProductRepo manufacturerProductRepo,
                              ManufacturerRepo manufacturerRepo, ProductRepo productRepo,
                              CartRepo cartRepo){
        this.userRepo = userRepo;
        this.manufacturerProductRepo = manufacturerProductRepo;
        this.manufacturerRepo = manufacturerRepo;
        this.productRepo = productRepo;
        this.cartRepo = cartRepo;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        manufacturerProductRepo.deleteAll();
        manufacturerRepo.deleteAll();
        productRepo.deleteAll();
        cartRepo.deleteAll();
        userRepo.deleteAll();

        var user = User.builder()
                .role("ROLE_USER")
                .email("user1@gmail.com")
                .firstname("roman")
                .lastname("romanov")
                .password(passwordEncoder.encode("12345"))
                .build();

        cartRepo.save(
                Cart.builder()
                        .user(user)
                        .build()
        );
        userRepo.save(user);

        userRepo.save(User.builder()
                .role("ROLE_ADMIN")
                .email("admin@gmail.com")
                .firstname("admin")
                .lastname("admin")
                .password(passwordEncoder.encode("admin"))
                .build());

        var manufacturer = Manufacturer.builder()
                .name("apple")
                .build();

        manufacturer = manufacturerRepo.save(manufacturer);

        var product = Product.builder()
                .name("phone")
                .build();
        product = productRepo.save(product);

        for(int i=0;i<30;i++) {
            manufacturerProductRepo.save(
                    ManufacturerProduct.builder()
                            .amount(i*10)
                            .price(i*100+i*10+i)
                            .product(product)
                            .manufacturer(manufacturer)
                            .description(String.valueOf(i))
                            .build()
            );
        }

    }
}
