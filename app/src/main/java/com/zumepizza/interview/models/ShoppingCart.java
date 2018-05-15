package com.zumepizza.interview.models;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private static final ShoppingCart cart = new ShoppingCart();
    private List<CartItem> cartItems = new ArrayList<>();


    public void addToCart(CartItem item) {
        for (CartItem cartItem: cartItems) {
            if (cartItem.getId() == item.getId()) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                return;
            }
        }
        item.setQuantity(1);
        cartItems.add(item);
    }

    public void decrementCartItem(CartItem item) {
        for (CartItem cartItem: cartItems) {
            if (cartItem.getId() == item.getId()){
                if (cartItem.getQuantity()==1) {
                    cartItems.remove(cartItem);
                    return;
                } else {
                    cartItem.setQuantity(cartItem.getQuantity() - 1);
                }
            }
        }
    }

    public void removeCartItem(CartItem item) {
        cartItems.remove(item);
    }

    public static ShoppingCart getInstance() {
        return cart;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "cartItems=" + cartItems.toString() +
                '}';
    }
}
