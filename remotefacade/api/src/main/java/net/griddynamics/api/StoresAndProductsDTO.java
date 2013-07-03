package net.griddynamics.api;

import net.griddynamics.api.services.Product;
import net.griddynamics.api.services.Store;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author one
 */
public class StoresAndProductsDTO implements Serializable{
    
    private List<Product> products;
    private List<Store> stores;

    public StoresAndProductsDTO(List<Product> products, List<Store> stores) {
        this.products = products;
        this.stores = stores;
    }

    public List<Product> getProducts() {
        return new ArrayList<Product>(products);
    }

    public List<Store> getStores() {
        return new ArrayList<Store>(stores);
    }

    @Override
    public String toString() {
        return "StoresWithProductsDTO{" + "products=" + products + ", stores=" + stores + '}';
    }
    
    
}