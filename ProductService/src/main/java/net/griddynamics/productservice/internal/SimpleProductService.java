/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.griddynamics.productservice.internal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import net.griddynamics.productservice.Product;
import net.griddynamics.productservice.ProductService;

/**
 *
 * @author one
 */
public class SimpleProductService implements ProductService {

    private static final Set<Product> products = new HashSet<Product>();
    public static final Product NOT_FOUND = new Product(0, "Not Found !");

    public SimpleProductService() {
        init();
    }

    private void init() {
        FileReader sourceFileReader;
        try {
            String sourceName = System.getProperty("user.home") + System.getProperty("file.separator")
                    + "init-params" + System.getProperty("file.separator") + "prods.txt";
            sourceFileReader = new FileReader(sourceName);
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }

        try {
            BufferedReader br = new BufferedReader(sourceFileReader);
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                String[] res = line.split(":");
                int id = Integer.parseInt(res[0].trim());
                String name = res[1].trim();
                Product product = new Product(id, name);
                products.add(product);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Product getProductByName(String name) {
        for (Product p : products) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return NOT_FOUND;
    }

    @Override
    public Product getProductByID(int id) {
        for (Product p : products) {
            if (p.getId() == id) {
                return p;
            }
        }
        return NOT_FOUND;
    }

    @Override
    public Set<Product> getAllProducts() {
        return new HashSet<Product>(products);
    }
}