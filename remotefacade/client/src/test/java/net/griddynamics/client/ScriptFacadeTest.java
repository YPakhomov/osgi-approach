/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.griddynamics.client;

import java.util.Arrays;
import java.util.List;
import net.griddynamics.api.Facade;
import net.griddynamics.api.ServiceException;
import net.griddynamics.api.StoresAndProductsDTO;
import net.griddynamics.api.services.Product;
import net.griddynamics.api.services.Store;
import net.griddynamics.api.script.ScriptFacade;
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
public class ScriptFacadeTest {

    private static RemoteFacadeHelper remoteFacadeHelper;
    private static Facade simpleFacade;
    private static Server server;

    public ScriptFacadeTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        remoteFacadeHelper = new RemoteFacadeHelper();
        ApplicationContext context = new ClassPathXmlApplicationContext("client-beans.xml");
        simpleFacade = context.getBean("simpleFacade", Facade.class);

        //------Jetty start------        
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
    public void sriptTest() throws ServiceException {
        List<Integer> ids = Arrays.asList(1, 2, 3, 4, 5);

        List<Product> expectedProducts = simpleFacade.getProducts(ids);

        ApplicationContext context = new ClassPathXmlApplicationContext("client-beans.xml");
        ScriptFacade scriptFacade = context.getBean("scriptFacade", ScriptFacade.class);
        List<Product> scriptResult = (List) scriptFacade.runScript("//groovy script\n"
                + "def resultList = []\n"
                + "for(id in [1,2,3,4,5]){\n"
                + "   tmpProduct = productService.getProductByID(id) \n"
                + "   if(tmpProduct.id != 0){ \n  "
                + "      resultList.add(tmpProduct)\n"
                + "   }\n"
                + "}\n"
                + "resultList\n");

        assertEquals(scriptResult, expectedProducts);
    }

    @Test
    public void StoresWithProductsTest() throws ServiceException {

        StoresAndProductsDTO storesWithProducts = simpleFacade.findStoresWithProducts("twix");
        List<Store> expectedStores = storesWithProducts.getStores();
        List<Product> expectedProducts = storesWithProducts.getProducts();

        ApplicationContext context = new ClassPathXmlApplicationContext("client-beans.xml");
        ScriptFacade scriptFacade = context.getBean("scriptFacade", ScriptFacade.class);

        StoresAndProductsDTO scriptResult = (StoresAndProductsDTO)scriptFacade.runScript(""
                + "def productIds = []\n"
                + "def resultProducts = productService.getProductsByName(\"twix\")\n"
                + "for(p in resultProducts){\n"
                + "    productIds.add(p.getId())\n"
                + "}\n"
                + "def resultStores = storeService.getStoresWithProducts(productIds)\n"
                + "new net.griddynamics.api.StoresAndProductsDTO(resultProducts, new ArrayList(resultStores))\n");


        List<Store> resultStores = ((StoresAndProductsDTO) scriptResult).getStores();
        List<Product> resultProducts = ((StoresAndProductsDTO) scriptResult).getProducts();
        assertEquals(expectedStores, resultStores);
        assertEquals(expectedProducts, resultProducts);
    }
}
//script template
//---------------------------------------------
//import net.griddynamics.server.services.SimpleProductService
//import net.griddynamics.api.StoresAndProductsDTO
//import net.griddynamics.server.services.SimpleStoreService
//
//
//def storeService = new SimpleStoreService();
//def productService = new SimpleProductService();
//
////---------copy from this point--------------
//def productIds = []
//def resultProducts = productService.getProductByName("twix")
//for(p in resultProducts){
//    productIds.add(p.getId())
//}
//def resultStores = storeService.getStoresWithProducts(productIds)
//new net.griddynamics.api.StoresAndProductsDTO(resultProducts, new ArrayList(resultStores))
