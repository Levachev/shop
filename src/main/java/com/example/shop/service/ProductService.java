package com.example.shop.service;

import com.example.shop.entity.Cart;
import com.example.shop.entity.CartProduct;
import com.example.shop.entity.ManufacturerProduct;
import com.example.shop.repo.CartProductRepo;
import com.example.shop.repo.CartRepo;
import com.example.shop.repo.ManufacturerProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ManufacturerProductRepo manufacturerProductRepo;

    @Autowired
    private CartProductRepo cartProductRepo;

    @Autowired
    private CartRepo cartRepo;

    public ManufacturerProduct getById(Long id){
        var tmp = manufacturerProductRepo.findById(id);

        return tmp.orElse(null);
    }

    @Transactional
    public void addToCart(Long userId, Long productId, int amount){
        Optional<ManufacturerProduct> manufacturerProductOptional = manufacturerProductRepo.findById(productId);

        Optional<Cart> cartOptional = cartRepo.findById(userId);

        if(cartOptional.isEmpty() || manufacturerProductOptional.isEmpty()){
            return;//TODO add status of successful
        }

        ManufacturerProduct manufacturerProduct = manufacturerProductOptional.get();
        Cart cart = cartOptional.get();

        if(manufacturerProduct.getAmount() - amount < 0){
            return;//TODO add status of successful
        }

        List<CartProduct> cartProducts = cartProductRepo.findByManufacturerProduct(manufacturerProduct.getId());
        if(!cartProducts.isEmpty()){
            CartProduct tmp = cartProducts.getFirst();
            tmp.setAmount(tmp.getAmount()+amount);
            cartProductRepo.save(tmp);
        } else{
            CartProduct cartProduct = CartProduct.builder()
                    .manufacturerProduct(manufacturerProduct)
                    .cart(cart)
                    .amount(amount)
                    .build();
            cartProductRepo.save(cartProduct);
        }

        manufacturerProduct.setAmount(manufacturerProduct.getAmount() - amount);
        manufacturerProductRepo.save(manufacturerProduct);
    }

    @Transactional
    public void deleteToCart(Long userId, Long productId, int amount){
        Optional<ManufacturerProduct> manufacturerProductOptional = manufacturerProductRepo.findById(productId);

        Optional<Cart> cartOptional = cartRepo.findById(userId);

        if(cartOptional.isEmpty() || manufacturerProductOptional.isEmpty()){
            return;//TODO add status of successful
        }

        ManufacturerProduct manufacturerProduct = manufacturerProductOptional.get();

        if(manufacturerProduct.getAmount() - amount < 0){
            return;//TODO add status of successful
        }

        List<CartProduct> cartProducts = cartProductRepo.findByManufacturerProduct(manufacturerProduct.getId());
        if(!cartProducts.isEmpty()){
            CartProduct tmp = cartProducts.getFirst();
            tmp.setAmount(tmp.getAmount()-amount);
            if(tmp.getAmount() <= 0){
                cartProductRepo.delete(tmp);
            } else {
                cartProductRepo.save(tmp);
            }

            manufacturerProduct.setAmount(manufacturerProduct.getAmount() + amount);
            manufacturerProductRepo.save(manufacturerProduct);
        }
    }
}
