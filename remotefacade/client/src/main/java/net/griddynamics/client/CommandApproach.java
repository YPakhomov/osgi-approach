/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.griddynamics.client;

import java.util.Arrays;
import java.util.List;
import net.griddynamics.api.approach3.CommandFacade;
import net.griddynamics.api.Product;
import net.griddynamics.api.Store;
import net.griddynamics.api.approach3.commands.FindAppropriateStores;
import net.griddynamics.api.approach3.commands.Forward;
import net.griddynamics.api.approach3.commands.GetProducts;
import net.griddynamics.api.approach3.commands.utils.GetPropertyFunc;
import net.griddynamics.api.approach3.commands.utils.TransformList;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author one
 */
public class CommandApproach {

    public static void findAppropriateStoresUsageExample() {
        ApplicationContext context = new ClassPathXmlApplicationContext("client-beans.xml");
        CommandFacade f = context.getBean("commandFacade", CommandFacade.class);

        List<Integer> ids = Arrays.asList(1,2);
        
                    
        GetProducts products = new GetProducts(new Forward<List<Integer>>(ids));
        
        TransformList tr = new TransformList(products, new GetPropertyFunc<Product, Integer>("id"));
        
        FindAppropriateStores cmd = new FindAppropriateStores(tr);
        
        new RemoteFacadeHelper().executeRemotely(tr,cmd);
        
                
        System.out.println("stores");
        for(Store s : cmd.getResult()){
            System.out.println(s);
        }
    }
   
    public static void main(String[] args) {
        findAppropriateStoresUsageExample();
    }
}
