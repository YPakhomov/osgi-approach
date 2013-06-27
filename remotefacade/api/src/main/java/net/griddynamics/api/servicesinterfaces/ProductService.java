/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.griddynamics.api.servicesinterfaces;

import java.util.Set;
import net.griddynamics.api.Product;

/**
 *
 * @author one
 */
public interface ProductService {

    Set<Product> getAllProducts();

    Product getProductByID(int id);

    Product getProductByName(String name);
    
}
