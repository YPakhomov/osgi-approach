/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.griddynamics.api.approach3.commands.utils;

import java.util.ArrayList;
import java.util.List;
import net.griddynamics.api.approach3.Visitor;
import net.griddynamics.api.approach3.commands.Command;

/**
 *
 * @author one
 */
public class TransformList<T,V> implements Command<List<V>>{
    private Command<List<T>> source;
    private Function<T,V> mapper; //something ?
    
    private List<V> resultList;

    public Command<List<T>> getSource() {
        return source;
    }

    public Function<T, V> getMapper() {
        return mapper;
    }
    
    

    public TransformList(Command<List<T>> source, Function<T, V> mapFunc) {
        this.source = source;
        this.mapper = mapFunc;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public void setResult(List<V> result) {
        resultList = new ArrayList<V>(result);
    }

    public List<V> getResult() {
        return new ArrayList<V>(resultList);
    }        
}
