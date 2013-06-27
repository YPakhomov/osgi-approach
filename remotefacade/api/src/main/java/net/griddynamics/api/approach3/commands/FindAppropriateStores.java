/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.griddynamics.api.approach3.commands;

import java.util.ArrayList;
import java.util.List;
import net.griddynamics.api.Store;
import net.griddynamics.api.approach3.Visitor;

/**
 *
 * @author one
 */
public class FindAppropriateStores implements Command<List<Store>>{
    
    private Command<List<Integer>> productsIDList;
    private List<Store> result;

    public FindAppropriateStores(Command<List<Integer>> productsIDList) {
        this.productsIDList = productsIDList;
    }

    public Command<List<Integer>> getProductsIDList() {
        return productsIDList;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public void setResult(List<Store> result) {
        this.result = new ArrayList<Store>(result);
    }

    public List<Store> getResult() {
        return new ArrayList<Store>(result);
    }    
}
