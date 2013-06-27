/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.griddynamics.client;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.griddynamics.api.approach3.CommandFacade;
import net.griddynamics.api.approach3.commands.Command;
import net.griddynamics.api.approach3.commands.CommandList;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * not finished yet
 * @author one
 */
public class RemoteFacadeHelper {
    
    private ApplicationContext context;
    
    public RemoteFacadeHelper() {
        context = new ClassPathXmlApplicationContext("client-beans.xml");
    }
    
    public void executeRemotely(Command<?>... commands) {
        
        CommandFacade facade = context.getBean("commandFacade", CommandFacade.class);

        List<Command<?>> incomeCommands = Arrays.asList(commands);
        
        CommandList commandsToServer = new CommandList(incomeCommands);

        Command<List<Command<?>>> resultCommand = facade.executeRemotely(commandsToServer);
        List<Command<?>> resultCommandList = resultCommand.getResult();
        
        for (Iterator<Command<?>> resultIterator = resultCommandList.iterator(),
                incomeIterator = incomeCommands.iterator(); resultIterator.hasNext() && incomeIterator.hasNext();) {
            Command<?> resCmd = resultIterator.next();
            Command incomeCmd = incomeIterator.next();
            
            incomeCmd.setResult(resCmd.getResult());
        }       
    }
}
