/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.griddynamics.api.approach3;

import net.griddynamics.api.approach3.commands.CommandList;
import net.griddynamics.api.approach3.commands.FindAppropriateStores;
import net.griddynamics.api.approach3.commands.GetProductByIDCommand;
import net.griddynamics.api.approach3.commands.Forward;
import net.griddynamics.api.approach3.commands.GetProducts;
import net.griddynamics.api.approach3.commands.utils.TransformList;


/**
 *
 * @author one
 */
public interface Visitor {
    public <T> void visit(Forward<T> f);
    public  void visit(GetProductByIDCommand getProd);
    public void visit(CommandList cmdSet);
    public void visit(GetProducts getProducts);
    public void visit(FindAppropriateStores findCommand);
    public void visit(TransformList transformList);
}
