/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.griddynamics.server;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import net.griddynamics.api.script.ScriptFacade;
import net.griddynamics.api.ServiceException;
import net.griddynamics.api.servicesinterfaces.ProductService;
import net.griddynamics.api.servicesinterfaces.StoreService;
import org.springframework.beans.factory.annotation.Required;



/**
 *
 * @author one
 */
public class SimpleScriptFacade implements ScriptFacade{
    private ProductService productService;
    private StoreService storeService;

    
    @Override
    public Object runScript(String script) throws ServiceException{
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("groovy");
        engine.put("productService", productService);
        engine.put("storeService", storeService);
        try {
            return engine.eval(script);
        } catch (ScriptException ex){
            throw new ServiceException(ex);
        }
    }

    @Required
    public void setProductService(ProductService productService) {
        this.productService = productService;
        //productService.
    }

    @Required
    public void setStoreService(StoreService storeService) {
        this.storeService = storeService;
    }
        
}
