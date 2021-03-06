/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.griddynamics.server;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.griddynamics.api.approach3.Context;
import net.griddynamics.api.services.Product;
import net.griddynamics.api.services.Store;
import net.griddynamics.api.approach3.Visitor;
import net.griddynamics.api.approach3.commands.Command;
import net.griddynamics.api.approach3.commands.CommandList;
import net.griddynamics.api.approach3.commands.FindAppropriateStores;
import net.griddynamics.api.approach3.commands.GetProductByIDCommand;
import net.griddynamics.api.approach3.commands.GetProducts;
import net.griddynamics.api.approach3.commands.GetProductsByName;
import net.griddynamics.api.approach3.commands.utils.TransformList;
import net.griddynamics.productservice.internal.SimpleProductService;


/**
 *
 * @author one
 */
public class CommandVisitor<T> implements Visitor {

    Context context;

    public CommandVisitor(Context context) {
        this.context = context;
    }

    @Override
    public <T> void visit(net.griddynamics.api.approach3.commands.Forward<T> f) {
    }

    @Override
    public void visit(GetProductByIDCommand getProd) {
        Command<Integer> id = getProd.getProdID();
        id.accept(this);
        int curID = id.getResult();
        Product resultProduct = context.getProductService().getProductByID(curID);
        getProd.setResult(resultProduct);
    }

    @Override
    public void visit(CommandList cmdList) {
        List<Command<?>> commands = cmdList.getCommandList();
        List<Command<?>> resultList = new ArrayList<Command<?>>(commands.size());
        for (Command c : commands) {
            c.accept(this);
            resultList.add(c);
        }
        cmdList.setResult(resultList);
    }

    @Override
    public void visit(GetProducts getProducts) {
        Command<List<Integer>> idsCommand = getProducts.getIdsCommand();
        idsCommand.accept(this);
        List<Integer> idList = idsCommand.getResult();
        List<Product> resultList = new ArrayList<Product>(idsCommand.getResult().size());
        for (Integer id : idsCommand.getResult()) {
            Product product = context.getProductService().getProductByID(id);
            if( ! product.equals(SimpleProductService.NOT_FOUND)){
                 resultList.add(product);
            }
        }
        getProducts.setResult(resultList);
    }

    @Override
    public void visit(FindAppropriateStores findCommand) {
        Command<List<Integer>> idCommand = findCommand.getProductsIDList();
        idCommand.accept(this);
        Set<Integer> ids = new HashSet<Integer>(idCommand.getResult());
        Set<Store> stores = context.getStoreService().getAllStores();
        List<Store> resultStores = new ArrayList<Store>(
                context.getStoreService().getStoresWithProducts(idCommand.getResult()));
        findCommand.setResult(resultStores);
    }

    @Override
    public void visit(TransformList transformList) {
        Command sourceCommand = transformList.getSource();
        sourceCommand.accept(this);
        List sourceList = (List)sourceCommand.getResult();
        List resultList = new ArrayList(sourceList.size());
        
        for(Object o : sourceList){
            resultList.add(transformList.getMapper().apply(o));
        }        
        transformList.setResult(resultList);        
    }

    @Override
    public void visit(GetProductsByName getProductsByName) {
        Command<String> productNameSubstringCommand = getProductsByName.getProductNameSubstringCommand(); 
        productNameSubstringCommand.accept(this);
        List<Product> resultProducts = context.getProductService().getProductsByName(productNameSubstringCommand.getResult());
        getProductsByName.setResult(resultProducts);
    }
}
