/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.griddynamics.facade.executor.api;

/**
 *
 * @author one
 */
public interface FacadeExecutor {
    public Object invoke(String bundleSymbolicName, String bundleVersion,
            String facadeInterfaceName, String methodeName, Object[] params) throws FacadeExecutorExeption;
}
