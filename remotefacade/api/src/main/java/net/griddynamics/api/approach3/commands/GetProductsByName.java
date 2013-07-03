/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.griddynamics.api.approach3.commands;

import java.util.ArrayList;
import java.util.List;
import net.griddynamics.api.approach3.Visitor;
import net.griddynamics.api.services.Product;


/**
 *
 * @author one
 */
public class GetProductsByName implements Command<List<Product>>{
    
    private Command<String> productNameSubstringCmd;
    private List<Product> resultProducts;

    public GetProductsByName(Command<String> productNameSubstring) {
        this.productNameSubstringCmd = productNameSubstring;
    }
    
    public Command<String> getProductNameSubstringCommand(){
        return productNameSubstringCmd;
    }
    
    public void accept(Visitor v) {
        v.visit(this);
    }

    public void setResult(List<Product> result) {
        this.resultProducts = new ArrayList<Product>(result);
    }

    public List<Product> getResult() {
        return new ArrayList<Product>(resultProducts);
    }
    
}
