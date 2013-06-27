/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.griddynamics.api;

import java.util.List;

/**
 *
 * @author one
 */
public interface Facade {
    
    public List<Product> getProducts(List<Integer> ids);
    
    public StoresWithProductsDTO findStoresWithProducts(List<Integer> ids);
    
}
