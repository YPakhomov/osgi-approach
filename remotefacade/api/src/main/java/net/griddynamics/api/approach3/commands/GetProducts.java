/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.griddynamics.api.approach3.commands;

import java.util.ArrayList;
import java.util.List;
import net.griddynamics.api.Product;
import net.griddynamics.api.approach3.Visitor;

/**
 * retrieves list of products by ids
 * @author one
 */
public class GetProducts implements Command<List<Product>>{
    
    private Command<List<Integer>> idsCommand;
    private List<Product> resultList;

    public GetProducts(Command<List<Integer>> idsCommand) {
        this.idsCommand = idsCommand;
    }

    public Command<List<Integer>> getIdsCommand() {
        return idsCommand;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public void setResult(List<Product> result) {
        resultList = new ArrayList<Product>(result);
    }

    public List<Product> getResult() {
        return  new ArrayList<Product>(resultList);
    }
}
