/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.shopping;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Hp
 */
public class Cart {

    private Map<Integer, ProductInCart> cart;

    public Map<Integer, ProductInCart> getCart() {
        return cart;
    }

    public void setCart(Map<Integer, ProductInCart> cart) {
        this.cart = cart;
    }

    public boolean add(ProductInCart productInCart) {
        boolean check = false;
        try {
            if (this.cart == null) {
                this.cart = new HashMap<>();
            }
            if (this.cart.containsKey(productInCart.getProduct().getId())) {
                ProductInCart existingProduct = this.cart.get(productInCart.getProduct().getId());
                int currentQuantity = existingProduct.getQuantity();
                existingProduct.setQuantity(currentQuantity + productInCart.getQuantity());
            } else {
                this.cart.put(productInCart.getProduct().getId(), productInCart);
            }
            check = true;
        } catch (Exception e) {
            // Handle exception
        }
        return check;
    }

    public boolean remove(int productId) {
        boolean check = false;
        try {
            if (this.cart != null) {
                if (this.cart.containsKey(productId)) {
                    this.cart.remove(productId);
                    check = true;
                }
            }
        } catch (Exception e) {
            // Handle exception
        }
        return check;
    }

    public boolean edit(int productId, ProductInCart product) {
        boolean check = false;
        try {
            if (this.cart != null) {
                if (this.cart.containsKey(productId)) {
                    this.cart.replace(productId, product);
                    check = true;
                }
            }
        } catch (Exception e) {
            // Handle exception
        }
        return check;
    }
    
    public Double getTotalBill(){
        Double total=0.0;
        for(ProductInCart pic: cart.values()){
            total+= pic.getQuantity()*pic.getProduct().getPrice();
        }
        return total;
    }
}
