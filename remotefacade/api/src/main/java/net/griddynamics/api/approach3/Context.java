/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.griddynamics.api.approach3;

import net.griddynamics.api.services.ProductService;
import net.griddynamics.api.services.StoreService;

/**
 *
 * @author one
 */
public interface Context {
    public ProductService getProductService();
    public StoreService getStoreService();
    //public <T> T getParameter(Class<T> cl);
    //public boolean putParameter(Object param);
}
