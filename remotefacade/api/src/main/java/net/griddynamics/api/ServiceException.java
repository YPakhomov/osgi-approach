/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.griddynamics.api;

/**
 * 
 * @author one
 */
public class ServiceException extends Throwable{

    public ServiceException() {
    }

    public ServiceException(String string) {
        super(string);
    }

    public ServiceException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public ServiceException(Throwable thrwbl) {
        super(thrwbl);
    }

    public ServiceException(String string, Throwable thrwbl, boolean bln, boolean bln1) {
        super(string, thrwbl, bln, bln1);
    }
}
