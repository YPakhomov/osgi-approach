/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.griddynamics.api.approach3.commands.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * @author one
 */
public class GetPropertyFunc<T, V> implements Function<T, V> {

    String propertyName;

    public GetPropertyFunc(String propertyName) {
        this.propertyName = propertyName;
    }

    public V apply(T param) {
        Method getter = null;
        try {
            getter = param.getClass().getDeclaredMethod("get"
                    + propertyName.substring(0, 1).toUpperCase()
                    + propertyName.substring(1), null);
        } catch (NoSuchMethodException ex) {
            throw new RuntimeException(ex);
        } catch (SecurityException ex) {
            throw new RuntimeException(ex);
        }

        try {
            return (V) getter.invoke(param, null);
        } catch (IllegalAccessException ex) {
            throw new RuntimeException(ex);
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException(ex);
        } catch (InvocationTargetException ex) {
            throw new RuntimeException(ex);
        }
    }
}
