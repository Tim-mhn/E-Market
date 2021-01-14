/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import model.Product;

/**
 *
 * @author Tim
 */
@ManagedBean
@ApplicationScoped
public class CatalogManager implements java.io.Serializable {
    
    private ArrayList<Product> products = new ArrayList<> ();
    private String nameInput;
    private int idInput;
    private Double priceInput;
    /**
     * @return the products
     */
    public ArrayList<Product> getProducts() {
        return products;
    }

    /**
     * @param products the products to set
     */
    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
    
    public CatalogManager() {}
    
    @PostConstruct
    public void initCatalog() {
        Product p1 = new Product(451545, "Tomato", 1.4);
        Product p2 = new Product(41241, "Oatmeal", 2.8);
        
        this.products.add(p1);
        this.products.add(p2);
    }

    /**
     * @return the nameInput
     */
    public String getNameInput() {
        return nameInput;
    }

    /**
     * @param nameInput the nameInput to set
     */
    public void setNameInput(String nameInput) {
        this.nameInput = nameInput;
    }

    /**
     * @return the idInput
     */
    public int getIdInput() {
        return idInput;
    }

    /**
     * @param idInput the idInput to set
     */
    public void setIdInput(int idInput) {
        this.idInput = idInput;
    }

    /**
     * @return the priceInput
     */
    public Double getPriceInput() {
        return priceInput;
    }

    /**
     * @param priceInput the priceInput to set
     */
    public void setPriceInput(Double priceInput) {
        this.priceInput = priceInput;
    }
    
    private void addProduct(Product p) {
        this.products.add(p);
    }
    
    
    public String createProduct() {
        Product p = new Product(this.idInput, this.nameInput, this.priceInput);
        this.addProduct(p);
        return "productCreateToCatalog";
    }
}
