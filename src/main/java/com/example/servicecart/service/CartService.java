package com.example.servicecart.service;

import com.example.servicecart.client.ProductClient;
import com.example.servicecart.client.UserClient;
import com.example.servicecart.entity.Cart;
import com.example.servicecart.model.Product;
import com.example.servicecart.model.User;
import com.example.servicecart.repository.CartDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartDao cartDao;

    @Autowired
    UserClient userClient;

    @Autowired
    ProductClient productClient;

    public void deleteCartItem(Integer cartId) {
        cartDao.deleteById(cartId);
    }

    public Cart addToCart(Integer productId) {
        Product product = productClient.getProductById(productId);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        String user = null;
        if(username != null) {
            user = userClient.getUserByUsername(username).getUserName();
        }

        List<Cart> cartList = cartDao.findByUserName(user);
        List<Cart> filteredList = cartList.stream().filter(x -> x.getProduct().getProductId() == productId).collect(Collectors.toList());

        if(filteredList.size() > 0) {
            return null;
        }

        if(product != null && user != null) {
            Cart cart = new Cart(product, username);
            return cartDao.save(cart);
        }

        return null;
    }

    public List<Cart> getCartDetails() {
        //String username = JwtRequestFilter.CURRENT_USER;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return cartDao.findByUserName(username);
    }
}
