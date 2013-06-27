/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.griddynamics.server;

import java.util.ArrayList;
import java.util.List;
import net.griddynamics.api.approach3.CommandFacade;
import net.griddynamics.api.approach3.Visitor;
import net.griddynamics.api.approach3.commands.Command;
import net.griddynamics.api.approach3.commands.CommandList;
import org.springframework.beans.factory.annotation.Required;

/**
 *
 * @author one
 */
public class SimpleCommandFacade implements CommandFacade{
    private ServerContext context;
            
    @Required
    public void setContext(ServerContext context) {
        this.context = context;
    }

     

    @Override
    public <T> Command<T> executeRemotely(Command<T> command) {
        Visitor cv = new CommandVisitor(context);
        command.accept(cv);
        return  command;
    }
}
