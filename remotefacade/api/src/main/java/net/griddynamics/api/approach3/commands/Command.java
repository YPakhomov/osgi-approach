/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.griddynamics.api.approach3.commands;

import java.io.Serializable;
import net.griddynamics.api.approach3.Visitor;


/**
 *
 * @author one
 */
public interface Command<T> extends Serializable{
    public void accept(Visitor v);
    public void setResult(T result);
    public T getResult();
}
