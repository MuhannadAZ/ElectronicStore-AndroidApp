package com.example.proj;

import java.util.ArrayList;
import java.util.List;

public class CartManager {

    private static CartManager instance;
    private List<Product> cartItems;

    private CartManager() {
        cartItems = new ArrayList<>();
    }

    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public void addToCart(Product product) {
        boolean itemExists = false;

        for (Product cartItem : cartItems) {
            if (cartItem.getName().equals(product.getName())) {
                cartItem.setQuantity(cartItem.getQuantity() + 1); // Increase quantity if item exists
                itemExists = true;
                break;
            }
        }

        if (!itemExists) {
            cartItems.add(product); // Add new item if it doesn't exist
        }
    }


    public List<Product> getCartItems() {
        return cartItems;
    }

    public double getTotalCost() {
        double total = 0;
        for (Product product : cartItems) {
            total += product.getTotalPrice(); // Use product's total price based on quantity
        }
        return total;
    }


    public void clearCart() {
        cartItems.clear();
    }

    public void removeFromCart(Product product) {
        cartItems.remove(product);
    }

    public void removeFromCart(int position) {
        cartItems.remove(position);
    }

}
