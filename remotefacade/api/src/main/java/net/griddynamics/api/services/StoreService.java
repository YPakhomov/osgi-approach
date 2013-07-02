/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.griddynamics.api.services;

import java.util.List;
import java.util.Set;

/**
 *
 * @author one
 */
public interface StoreService {
    
    public Store getStoreByID(int id);
    public Set<Store> getAllStores();
    public Set<Store> getStoresWithProducts(List<Integer> productIds);
    
}
