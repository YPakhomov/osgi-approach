/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.griddynamics.facade.executor.osgi.internal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import net.griddynamics.facade.executor.api.FacadeExecutor;
import net.griddynamics.facade.executor.api.FacadeExecutorExeption;
import org.apache.felix.bundlerepository.Repository;
import org.apache.felix.bundlerepository.RepositoryAdmin;
import org.apache.felix.bundlerepository.Resolver;
import org.apache.felix.bundlerepository.Resource;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Version;
import org.osgi.util.tracker.ServiceTracker;

/**
 *
 * @author one
 */
public class SimpleFacadeExecutorOSGi implements FacadeExecutor {

    private BundleContext context;
    private ServiceTracker repositoryAdminServiceTracker;
    private Set<Repository> repos = new HashSet();

    public SimpleFacadeExecutorOSGi(BundleContext context, ServiceTracker repositoryAdminServiceTracker) {
        this.context = context;
        this.repositoryAdminServiceTracker = repositoryAdminServiceTracker;
    }

    @Override
    public Object invoke(String bundleSymbolicName, String bundleVersion,
            String facadeInterfaceName, String methodeName, Object[] params) throws FacadeExecutorExeption {
        try {
            
            addRepository("http://localhost:8081/nexus/content/repositories/local-osgi-release/repository.xml");
            
            if(!isBundleInstalled(bundleSymbolicName, bundleVersion)){ // is NOT installed check
                installBundleFromRepository(bundleSymbolicName, bundleVersion);
                
                Version bVersion = new Version(bundleVersion);
                for(Bundle b : context.getBundles()){
                    if(b.getSymbolicName().equals(bundleSymbolicName) && b.getVersion().equals(bVersion)){
                        System.out.println("Starting " + b.getSymbolicName() +" #" + b.getVersion());
                        b.start();
                        break;
                    }
                }
            } else {
                System.out.println(bundleSymbolicName + "#" + bundleVersion + " is installed !");
            }
                      
            

            ServiceTracker serviceTracker = new ServiceTracker(context, facadeInterfaceName, null); 
            serviceTracker.open();

            Object service = serviceTracker.getService();

            if (service == null) {
                throw new FacadeExecutorExeption("No service registered with thename" + facadeInterfaceName);
            }

            Method declaredMethod = null;
            for (Method m : service.getClass().getDeclaredMethods()) {
                if (m.getName().equals(methodeName)) {
                    declaredMethod = m;
                }
            }

            if (declaredMethod == null) {
                throw new NoSuchMethodException("No method " + methodeName
                        + "in class " + facadeInterfaceName);
            }

            Object invoke = declaredMethod.invoke(service, params);
            
            serviceTracker.close();
            return invoke;

        } catch (NoSuchMethodException ex) {
            throw new RuntimeException(ex);
        } catch (SecurityException ex) {
            throw new RuntimeException(ex);
        } catch (IllegalAccessException ex) {
            throw new RuntimeException(ex);
        } catch (InvocationTargetException ex) {
            throw new RuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private boolean isBundleInstalled(String bundleSymbolicName, String bundleVersion) {

        boolean isInstalled = false;
        Version bVersion = new Version(bundleVersion);
        for (Bundle b : context.getBundles()) {
            if (b.getSymbolicName().equals(bundleSymbolicName) && b.getVersion().equals(bVersion)) {
                isInstalled = true;
                System.out.println(b.getSymbolicName() + " version: " + b.getVersion());
            }
        }
        return isInstalled;
    }

    public void addRepository(String repository) throws Exception {

        RepositoryAdmin repositoryAdminService = (RepositoryAdmin) repositoryAdminServiceTracker.getService();
        Repository validLocalRepo = repositoryAdminService.addRepository(repository);
        repos.add(validLocalRepo);
    }

    private void installBundleFromRepository(String bundleSymbolicName, String bundleVersion) {

        RepositoryAdmin repositoryAdminService = (RepositoryAdmin) repositoryAdminServiceTracker.getService();

        Resolver resolver = repositoryAdminService.resolver();
        Resource myRes = null;
        Version bVersion = new Version(bundleVersion);

        outer:
        for (Repository repository : repos) {
            for (Resource res : repository.getResources()) {
                if (res.getSymbolicName().equals(bundleSymbolicName) && res.getVersion().equals(bVersion)) {
                    myRes = res;
                    break outer;
                }
            }
        }

        if (myRes == null) {
            throw new FacadeExecutorExeption(bundleSymbolicName + "#" + bundleVersion + " such bundle is not found !");
        }

        resolver.add(myRes);

        if (resolver.resolve()) {
            resolver.deploy(0);
        }
    }
}
