/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.griddynamics.api;

import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author one
 */
public class Store implements Serializable{
    private int id;
    private String name;
    private String district;
    private final Collection<Integer> products;

    public Store(int id, String name, String district, Collection<Integer> products) {
        this.id = id;
        this.name = name;
        this.district = district;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDistrict() {
        return district;
    }

    public Collection<Integer> getProducts() {
        return products;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.id;
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
        final Store other = (Store) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Store{" + "id=" + id + ", name=" + name + ", district=" + district + ", products=" + products + '}';
    }   
}
