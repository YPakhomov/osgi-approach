package net.griddynamics.simplefacadeb.internal;

import net.griddynamics.simplefacadeb.FacadeB;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

    public void start(BundleContext context) throws Exception {
        ServiceRegistration<FacadeB> registerService = 
                context.registerService(FacadeB.class, new SimpleFacadeB(), null);
    }

    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }

}
