/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.griddynamics.api.approach3.commands.utils;


import net.griddynamics.api.Product;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author one
 */
public class GetPropertyFuncTest {
    
    public GetPropertyFuncTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of map method, of class GetPropertyFunc.
     */
    @Test
    public void testMap() {
        System.out.println("GetPropertyFunc.map() - test");
        String expResult = "testParam";
        Product param = new Product(15, "testParam");
        GetPropertyFunc<Product,String> instance = new GetPropertyFunc<Product,String>("name");
        
        String result = instance.apply(param);
        assertEquals(expResult, result);
        System.out.println(result);
    }
    
    @Test
    public void testMapID(){
        System.out.println("map() - id");
        Integer expResult = 150;
        Product param = new Product(150, "testParam");
        GetPropertyFunc<Product,Integer> instance = new GetPropertyFunc<Product,Integer>("id");
        
        Integer result = instance.apply(param);
        assertEquals(expResult, result);
        
        System.out.println(result);
    }
}