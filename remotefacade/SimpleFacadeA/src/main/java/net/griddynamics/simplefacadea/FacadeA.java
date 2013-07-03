/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.griddynamics.simplefacadea;

import java.util.List;
import net.griddynamics.api.StoresAndProductsDTO;
import net.griddynamics.api.services.Product;


/**
 *
 * @author one
 */
public interface FacadeA {
    public List<Product> getProducts(List<Integer> ids);
    public StoresAndProductsDTO findStoresWithProducts(String productNameSubstring);
}
