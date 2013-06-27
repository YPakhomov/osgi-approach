/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.griddynamics.api.approach3;

import net.griddynamics.api.approach3.commands.Command;

/**
 *
 * @author one
 */
public interface CommandFacade {
    public <T> Command<T> executeRemotely(Command<T> command);
}
