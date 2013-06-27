/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.griddynamics.api.approach3.commands;

import java.util.ArrayList;
import java.util.List;
import net.griddynamics.api.approach3.Visitor;

/**
 *
 * @author one
 */
public class CommandList implements Command<List<Command<?>>>{
    
    private List<Command<?>> commandList;
    private List<Command<?>> resultList;

    public CommandList(List<Command<?>> commandList) {
        this.commandList = new ArrayList<Command<?>>(commandList);
    }
    
    public void accept(Visitor v) {
        v.visit(this);
    }
    
    public List<Command<?>> getCommandList(){
        return new ArrayList<Command<?>>(commandList);
    }
    
    public void setResult(List<Command<?>> result) {
        resultList = new ArrayList<Command<?>>(result);
    }

    public List<Command<?>> getResult() {
        return new ArrayList<Command<?>>(resultList);
    }
}
