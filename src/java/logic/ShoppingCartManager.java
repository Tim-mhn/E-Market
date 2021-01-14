/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.ArrayList;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Product;
import model.ShoppingCartItem;

/**
 *
 * @author Tim
 */
@ManagedBean
@SessionScoped
public class ShoppingCartManager implements java.io.Serializable {
    private ArrayList<ShoppingCartItem> shoppingCart ;
    private Product prodToAdd;
    
    public ShoppingCartManager() {
        this.shoppingCart = new ArrayList<> ();
    }

    /**
     * @return the shoppingCart
     */
    public ArrayList<ShoppingCartItem> getShoppingCart() {
        return shoppingCart;
    }

    /**
     * @param shoppingCart the shoppingCart to set
     */
    public void setShoppingCart(ArrayList<ShoppingCartItem> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
    
    private void addShoppingCartItem(ShoppingCartItem sci) {
        this.shoppingCart.add(sci);
    }
    
    public String addToCart() {
        
        ShoppingCartItem existingItem = this.shoppingCart.stream()
                .filter(item -> item.getProduct().getName().equals(this.prodToAdd.getName()))
                .findFirst()
                .orElse(null);
        
        // Item is not in shopping cart -> create new Shopping Cart Item
        if (existingItem == null) {
            int id = new Random().nextInt(1000000);
            ShoppingCartItem sci = new ShoppingCartItem(id, 1, this.prodToAdd);
            this.addShoppingCartItem(sci);
        } 
        // Item is already in shopping cart -> increment quantity by 1
        else {
            existingItem.incrementQuantity();
        }
        return "catalogToShoppingCart";

    }
    
    @PostConstruct
    public void initCart() {
        Product p = new Product(4514, "Tomato", 1.4);
        ShoppingCartItem sci = new ShoppingCartItem(451424, 2, p);
        
        this.addShoppingCartItem(sci);
        
    }

    /**
     * @return the prodToAdd
     */
    public Product getProdToAdd() {
        return prodToAdd;
    }
    
    public Double computeTotalPrice() {
        Double price = this.shoppingCart
                .stream()
                .reduce(0., (subTotal, sci) -> subTotal + sci.getProduct().getPrice() * sci.getQuantity(), Double::sum);
        
        return Math.round(price * 100.0) / 100.0;
    }

    /**
     * @param prodToAdd the prodToAdd to set
     */
    public void setProdToAdd(Product prodToAdd) {
        this.prodToAdd = prodToAdd;
    }
    
    public void removeFromCart(ShoppingCartItem sci) {
        this.shoppingCart.remove(sci);
    }
    
}
