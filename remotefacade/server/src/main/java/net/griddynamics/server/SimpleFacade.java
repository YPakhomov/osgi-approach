/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.griddynamics.server;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.griddynamics.api.Facade;
import net.griddynamics.api.StoresWithProductsDTO;
import net.griddynamics.api.services.Product;
import net.griddynamics.api.services.Store;
import net.griddynamics.api.services.ProductService;
import net.griddynamics.api.services.StoreService;
import net.griddynamics.server.services.SimpleProductService;
import org.springframework.beans.factory.annotation.Required;

/**
 *
 * @author one
 */
public class SimpleFacade implements Facade{
    
    private ProductService productService;
    private StoreService storeService;
    
    @Override
    public List<Product> getProducts(List<Integer> ids) {
        List<Product> products = new ArrayList<Product>(ids.size());
        Product tmpProduct;
        for(int id : ids){
            tmpProduct = productService.getProductByID(id);
            if( ! tmpProduct.equals(SimpleProductService.NOT_FOUND) ){
                products.add(tmpProduct);
            }
        }
        return products;
    }

    @Override
    public StoresWithProductsDTO findStoresWithProducts(List<Integer> ids) {
        Set<Store> stores = storeService.getAllStores();
        Set<Product> products = new HashSet<Product>(getProducts(ids));
        
        List<Store> resultStores = new ArrayList<Store>();
        
        for(Store s : stores){
            boolean containsAll = true;
            Set<Product> productsInCurrentStore = new HashSet<Product>(getProducts(new ArrayList(s.getProducts())));
            for(Product prod : products){
                if( ! productsInCurrentStore.contains(prod)){
                    containsAll = false;
                    break;
                }
            }
            if(containsAll){
                resultStores.add(s);
            }
        }
        return new StoresWithProductsDTO(new ArrayList<Product>(products), resultStores);
    }
    
    @Required
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Required
    public void setStoreService(StoreService storeService) {
        this.storeService = storeService;
    }
    
}
