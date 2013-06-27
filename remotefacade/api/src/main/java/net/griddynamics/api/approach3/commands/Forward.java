/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.griddynamics.api.approach3.commands;

import net.griddynamics.api.approach3.Visitor;

/**
 *
 * @author one
 */
public class Forward<T> implements Command<T>{
    private T param;

    public Forward(T param) {
        this.param = param;
    }
    
    public void accept(Visitor v) {
        //v.visit(this); //
    }

    public T getResult() {
        return param;
    }

    public void setResult(T result) {
         param = result; 
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.param != null ? this.param.hashCode() : 0);
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
        final Forward<T> other = (Forward<T>) obj;
        if (this.param != other.param && (this.param == null || !this.param.equals(other.param))) {
            return false;
        }
        return true;
    }   
    
}
