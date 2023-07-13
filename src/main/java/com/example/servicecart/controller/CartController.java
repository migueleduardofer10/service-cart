package com.example.servicecart.controller;

import com.example.servicecart.entity.Cart;
import com.example.servicecart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/carts/{productId}")
    public Cart addToCart(@PathVariable(name = "productId") Integer productId) {
        return cartService.addToCart(productId);
    }

    @DeleteMapping("/carts/{cartId}")
    public void deleteCartItem(@PathVariable(name = "cartId") Integer cartId) {
        cartService.deleteCartItem(cartId);
    }

    @GetMapping("/carts")
    public List<Cart> getCartDetails() {
        return cartService.getCartDetails();
    }
}
