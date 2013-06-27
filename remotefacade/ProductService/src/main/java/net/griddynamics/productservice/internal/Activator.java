package net.griddynamics.productservice.internal;


import net.griddynamics.api.servicesinterfaces.ProductService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

    public void start(BundleContext context) throws Exception {
        //TODO should create ProductService and publish it  to osgi how?
        ServiceRegistration<ProductService> registerService =        
                context.registerService(ProductService.class, new SimpleProductService(), null);
        
        
    }

    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }
}
