package com.example.shop.service;

import com.example.shop.entity.Cart;
import com.example.shop.entity.CartProduct;
import com.example.shop.entity.ManufacturerProduct;
import com.example.shop.models.SuccessEnum;
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
public class CartServiceImpl implements CartService {

    @Autowired
    private ManufacturerProductRepo manufacturerProductRepo;

    @Autowired
    private CartProductRepo cartProductRepo;

    @Autowired
    private CartRepo cartRepo;

    @Override
    @Transactional
    public SuccessEnum addToCart(Long userId, Long productId, int amount){
        Optional<ManufacturerProduct> manufacturerProductOptional = manufacturerProductRepo.findById(productId);

        Optional<Cart> cartOptional = cartRepo.findById(userId);

        if(cartOptional.isEmpty() || manufacturerProductOptional.isEmpty()){
            return SuccessEnum.FAIL;
        }

        ManufacturerProduct manufacturerProduct = manufacturerProductOptional.get();
        Cart cart = cartOptional.get();

        if(manufacturerProduct.getAmount() - amount < 0){
            return SuccessEnum.FAIL;
        }

        Optional<CartProduct> cartProducts = cartProductRepo.findByManufacturerProductAndCart(manufacturerProduct.getId(), cart.getId());
        if(cartProducts.isPresent()){
            CartProduct tmp = cartProducts.get();
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

        return SuccessEnum.SUCCESS;
    }

    @Override
    @Transactional
    public SuccessEnum deleteFromCart(Long userId, Long productId, int amount){
        Optional<ManufacturerProduct> manufacturerProductOptional = manufacturerProductRepo.findById(productId);

        Optional<Cart> cartOptional = cartRepo.findById(userId);

        if(cartOptional.isEmpty() || manufacturerProductOptional.isEmpty()){
            return SuccessEnum.FAIL;
        }

        ManufacturerProduct manufacturerProduct = manufacturerProductOptional.get();
        Cart cart = cartOptional.get();

        Optional<CartProduct> cartProducts = cartProductRepo.findByManufacturerProductAndCart(manufacturerProduct.getId(), cart.getId());
        if(cartProducts.isPresent()){
            CartProduct tmp = cartProducts.get();
            tmp.setAmount(tmp.getAmount()-amount);
            if(tmp.getAmount() <= 0){
                cartProductRepo.delete(tmp);
            } else {
                cartProductRepo.save(tmp);
            }

            manufacturerProduct.setAmount(manufacturerProduct.getAmount() + amount);
            manufacturerProductRepo.save(manufacturerProduct);

            return SuccessEnum.SUCCESS;
        }

        return SuccessEnum.FAIL;
    }

    @Override
    @Transactional
    public SuccessEnum order(Long userId){
        Optional<Cart> cartOptional = cartRepo.findById(userId);

        if(cartOptional.isEmpty()){
            return  SuccessEnum.FAIL;
        }

        Cart cart = cartOptional.get();

        for(CartProduct product : cart.getCartProducts()){
            cartProductRepo.delete(product);
        }

        return SuccessEnum.SUCCESS;
    }

    @Override
    public List<CartProduct> show(Long userId, int page){
        int pageNumber = page < 1 ? 0 : page-1;
        int pageSize = 10;

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return cartProductRepo.findByCart(pageable, userId).getContent();
    }
}
