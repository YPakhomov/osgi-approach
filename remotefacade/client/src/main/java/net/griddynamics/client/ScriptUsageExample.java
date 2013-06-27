package net.griddynamics.client;

import javax.script.ScriptException;
import net.griddynamics.api.script.ScriptFacade;
import net.griddynamics.api.ServiceException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ScriptUsageExample{
    public static void main( String[] args ) throws ScriptException, ServiceException{
        ApplicationContext context = new ClassPathXmlApplicationContext("client-beans.xml");
        ScriptFacade f = context.getBean("scriptFacade", ScriptFacade.class);
        
        System.out.println(f.runScript("productService.getAllProducts()"));
        System.out.println(f.runScript("storeService.getAllStores()"));
    }
}
