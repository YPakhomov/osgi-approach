package net.griddynamics.client.osgi;

import net.griddynamics.facade.executor.api.FacadeExecutor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("client-beans.xml");
        FacadeExecutor f = context.getBean("osgiFacade", FacadeExecutor.class);
        
//        Object[] params = {"Billy"};//{(Object)Arrays.asList(1,2,3,7)};
//        
//        Object invoke = f.invoke("net.griddynamics.simplefacadeb", "1.1.8", "net.griddynamics.simplefacadeb.FacadeB",
//                                "someMethod", params );
        
        Object[] params = {"twix"};//{(Object)Arrays.asList(1,2,3,7)};
        
        Object invoke = f.invoke("net.griddynamics.SimpleFacadeA", "1.0.0", "net.griddynamics.simplefacadea.FacadeA",
                                "findStoresWithProducts", params );
        System.out.println(invoke);
        
    }
}
