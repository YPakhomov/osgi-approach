/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.griddynamics.client;

import java.util.Arrays;
import java.util.List;
import net.griddynamics.api.Facade;
import net.griddynamics.api.StoresWithProductsDTO;
import net.griddynamics.api.services.Product;
import net.griddynamics.api.services.Store;
import net.griddynamics.api.approach3.commands.Command;
import net.griddynamics.api.approach3.commands.FindAppropriateStores;
import net.griddynamics.api.approach3.commands.Forward;
import net.griddynamics.api.approach3.commands.GetProducts;
import net.griddynamics.api.approach3.commands.utils.GetPropertyFunc;
import net.griddynamics.api.approach3.commands.utils.TransformList;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author one
 */
public class CommandApproachTest {

    private static RemoteFacadeHelper remoteFacadeHelper;
    private static Facade simpleFacade;
    private static Server server;

    public CommandApproachTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        remoteFacadeHelper = new RemoteFacadeHelper();
        ApplicationContext context = new ClassPathXmlApplicationContext("client-beans.xml");
        simpleFacade = context.getBean("simpleFacade", Facade.class);
        
        //------Jetty start        
        server = new Server(8080);
        WebAppContext root = new WebAppContext();
        
        root.setWar("./../server/target/server-1.0.war");
        root.setContextPath("/server");
        
        server.setHandler(root);
        server.start();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        server.stop();
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getProductsTest() {
        List<Integer> ids = Arrays.asList(1, 2, 3, 4, 5);
        List<Product> expectedProducts = simpleFacade.getProducts(ids);
        Command<List<Product>> remoteCommand = new GetProducts(new Forward<List<Integer>>(ids));
        remoteFacadeHelper.executeRemotely(remoteCommand);
        assertEquals(remoteCommand.getResult(), expectedProducts);
    }

    @Test
    public void FindStoresWithProductsTest() {
        List<Integer> ids = Arrays.asList(1, 2, 3, 4, 5);
        StoresWithProductsDTO storesWithProducts = simpleFacade.findStoresWithProducts(ids);
        List<Store> expectedStores = storesWithProducts.getStores();

        Command<List<Product>> existingProducts = new GetProducts(new Forward<List<Integer>>(ids));
        TransformList<Product, Integer> idsOfexistingProducts =
                new TransformList<Product, Integer>(existingProducts, new GetPropertyFunc<Product, Integer>("id"));
        FindAppropriateStores resultStoresCommand = new FindAppropriateStores(idsOfexistingProducts);

        remoteFacadeHelper.executeRemotely(resultStoresCommand, existingProducts);
        assertEquals(expectedStores, resultStoresCommand.getResult());
    }

    
}