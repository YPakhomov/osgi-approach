package net.griddynamics.simplefacadea.internal;


import net.griddynamics.api.services.ProductService;
import net.griddynamics.simplefacadea.FacadeA;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {

    public void start(BundleContext context) throws Exception {
        // TODO add activation code here
        ServiceReference<ProductService> produServiceReference 
                = context.getServiceReference(ProductService.class);
        ProductService productService = context.getService(produServiceReference);
        
        context.registerService(FacadeA.class, new SimpleFacadeA(productService), null);
        
    }

    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }

}
