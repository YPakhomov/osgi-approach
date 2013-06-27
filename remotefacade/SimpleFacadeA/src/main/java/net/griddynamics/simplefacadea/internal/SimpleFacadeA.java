/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.griddynamics.simplefacadea.internal;

import java.util.ArrayList;
import java.util.List;
import net.griddynamics.api.Product;
import net.griddynamics.api.servicesinterfaces.ProductService;
import net.griddynamics.api.servicesinterfaces.StoreService;
import net.griddynamics.simplefacadea.FacadeA;

/**
 *
 * @author one
 */
public class SimpleFacadeA implements FacadeA {

    ProductService productService;
    StoreService storeService;

    public SimpleFacadeA(ProductService productService) {
        this.productService = productService;
    }

    public List<Product> getProducts(List<Integer> ids) {
        List<Product> result = new ArrayList<Product>();
        for (Integer i : ids) {
            result.add(productService.getProductByID(i));
        }
        return result;
    }
}
