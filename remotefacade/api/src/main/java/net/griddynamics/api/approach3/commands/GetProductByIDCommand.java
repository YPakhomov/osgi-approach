/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.griddynamics.api.approach3.commands;

import net.griddynamics.api.Product;
import net.griddynamics.api.approach3.Visitor;

/**
 *
 * @author one
 */
public class GetProductByIDCommand implements Command<Product>{
    private Command<Integer> prodID;
    private Product resultProduct;
    
    public GetProductByIDCommand(Command<Integer> param) {
        this.prodID = param;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }   

    public void setResult(Product result) {
        resultProduct = result;                
    }

    public Product getResult() {
        return resultProduct;
    }

    public Command<Integer> getProdID() {
        return prodID;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.prodID != null ? this.prodID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GetProductByIDCommand other = (GetProductByIDCommand) obj;
        if (this.prodID != other.prodID && (this.prodID == null || !this.prodID.equals(other.prodID))) {
            return false;
        }
        return true;
    }   
}
