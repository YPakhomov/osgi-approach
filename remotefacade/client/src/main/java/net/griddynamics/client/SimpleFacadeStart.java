/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.griddynamics.client;

import java.util.Arrays;
import net.griddynamics.api.Facade;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author one
 */
public class SimpleFacadeStart {
    
    
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("client-beans.xml");
        Facade f = context.getBean("simpleFacade", Facade.class);
        
        System.out.println(f.findStoresWithProducts(Arrays.asList(1,2,3)));
    }
}
