/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package implementation;

/**
 *
 * @author one
 */
public class SimpleFacadeA implements facadeapi.FacadeA {

    public String greet(String name) {
        return "Hello from SimpleFacadeA  " + name;
    }
}
