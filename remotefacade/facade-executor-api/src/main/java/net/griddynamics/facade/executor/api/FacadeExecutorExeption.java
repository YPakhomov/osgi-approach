/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.griddynamics.facade.executor.api;

/**
 *
 * @author one
 */
public class FacadeExecutorExeption extends RuntimeException{

    public FacadeExecutorExeption() {
    }

    public FacadeExecutorExeption(String string) {
        super(string);
    }

    public FacadeExecutorExeption(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public FacadeExecutorExeption(Throwable thrwbl) {
        super(thrwbl);
    }
}
