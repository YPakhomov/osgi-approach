/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.griddynamics.productservice;

import java.util.Set;


/**
 *
 * @author one
 */
public interface ProductService {

    Set<Product> getAllProducts();

    Product getProductByID(int id);

    Product getProductByName(String name);
    
}
