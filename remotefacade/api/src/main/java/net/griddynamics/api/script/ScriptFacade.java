/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.griddynamics.api.script;

import net.griddynamics.api.ServiceException;


/**
 *
 * @author one
 */
public interface ScriptFacade {
    public Object runScript(String script) throws ServiceException;
}
