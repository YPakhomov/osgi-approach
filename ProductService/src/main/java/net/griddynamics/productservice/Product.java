/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.griddynamics.productservice;

import java.io.Serializable;

/**
 *
 * @author one
 */
public class Product implements Serializable{
    private Integer id;
    private String name;

    public Product(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    //getters
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    //setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
        
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.id;
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
        final Product other = (Product) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + '}';
    }
}
