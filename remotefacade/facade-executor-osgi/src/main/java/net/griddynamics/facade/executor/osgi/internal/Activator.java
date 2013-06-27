package net.griddynamics.facade.executor.osgi.internal;

import net.griddynamics.facade.executor.api.FacadeExecutor;
import org.apache.felix.bundlerepository.RepositoryAdmin;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.ServiceTracker;

public class Activator implements BundleActivator {
    
    private ServiceTracker repositoryAdminServiceTracker;
    
    public void start(BundleContext context) throws Exception {
        
        repositoryAdminServiceTracker = new ServiceTracker(context, RepositoryAdmin.class, null);
        repositoryAdminServiceTracker.open();
        ServiceRegistration<FacadeExecutor> registerService =
                context.registerService(FacadeExecutor.class,
                new SimpleFacadeExecutorOSGi(context,repositoryAdminServiceTracker), null);
    }

    public void stop(BundleContext context) throws Exception {
        repositoryAdminServiceTracker.close();
    }
}
