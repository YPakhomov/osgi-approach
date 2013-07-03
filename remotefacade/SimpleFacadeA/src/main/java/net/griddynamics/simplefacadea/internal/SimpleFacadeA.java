/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.griddynamics.simplefacadea.internal;

import java.util.ArrayList;
import java.util.List;
import net.griddynamics.api.StoresAndProductsDTO;
import net.griddynamics.api.services.Product;
import net.griddynamics.api.services.ProductService;
import net.griddynamics.api.services.Store;
import net.griddynamics.api.services.StoreService;
import net.griddynamics.simplefacadea.FacadeA;

/**
 *
 * @author one
 */
public class SimpleFacadeA implements FacadeA {

    ProductService productService;
    StoreService storeService;

    public SimpleFacadeA(ProductService productService, StoreService storeService) {
        this.productService = productService;
        this.storeService = storeService;
    }

    public List<Product> getProducts(List<Integer> ids) {
        List<Product> result = new ArrayList<Product>();
        for (Integer i : ids) {
            result.add(productService.getProductByID(i));
        }
        return result;
    }

    public StoresAndProductsDTO findStoresWithProducts(String productNameSubstring) {
        List<Product> products = productService.getProductsByName(productNameSubstring);
        List<Integer> productIds = new ArrayList<Integer>(products.size());
        for(Product p : products){
            productIds.add(p.getId());
        }
        List<Store> storesWithProducts = new ArrayList<Store>(storeService.getStoresWithProducts(productIds));
        return new StoresAndProductsDTO(products, storesWithProducts);
    }
}
