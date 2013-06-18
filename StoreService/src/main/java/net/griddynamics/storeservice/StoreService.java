/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.griddynamics.storeservice;

import java.util.Set;

/**
 *
 * @author one
 */
public interface StoreService {
    
    public Store getStoreByID(int id);
    public Set<Store> getAllStores();
    
}
