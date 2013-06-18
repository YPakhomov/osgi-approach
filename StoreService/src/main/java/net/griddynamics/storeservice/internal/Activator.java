package net.griddynamics.storeservice.internal;

import net.griddynamics.storeservice.StoreService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    public void start(BundleContext context) throws Exception {
        
        context.registerService(StoreService.class, new SimpleStoreService(), null);
        
    }

    public void stop(BundleContext context) throws Exception {
    }

}
