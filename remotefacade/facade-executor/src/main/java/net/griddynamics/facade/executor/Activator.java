package net.griddynamics.facade.executor;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    public void start(BundleContext context) throws Exception {
        for(Bundle b : context.getBundles()){
            System.out.println(b);
        }
    }

    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }

}
