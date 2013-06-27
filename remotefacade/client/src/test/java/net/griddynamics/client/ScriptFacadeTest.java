/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.griddynamics.client;

import java.util.Arrays;
import java.util.List;
import net.griddynamics.api.Facade;
import net.griddynamics.api.ServiceException;
import net.griddynamics.api.StoresWithProductsDTO;
import net.griddynamics.api.Product;
import net.griddynamics.api.Store;
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
        
        List<Integer> ids = Arrays.asList(1, 2);
        StoresWithProductsDTO storesWithProducts = simpleFacade.findStoresWithProducts(ids);
        List<Store> expectedStores = storesWithProducts.getStores();
        
        ApplicationContext context = new ClassPathXmlApplicationContext("client-beans.xml");
        ScriptFacade scriptFacade = context.getBean("scriptFacade", ScriptFacade.class);

        Object scriptResult = scriptFacade.runScript(""
                + "import net.griddynamics.api.StoresWithProductsDTO\n"
                + "def resultProducts = []\n"
                + "def resultStores = []\n"
                + "def idList = [1,2]\n"
                + "for(id in idList){\n"
                + "    tmpProduct = productService.getProductByID(id)\n"
                + "    if(tmpProduct.id != 0){ \n"
                + "        resultProducts.add(tmpProduct)\n"
                + "    }\n"
                + "}\n"
                + "def stores = storeService.getAllStores()\n"
                + "for(s in stores){\n"
                + "    def containsAll = true\n"
                + "    def products = new HashSet(s.getProducts())\n"
                + "    for(id in idList){\n"
                + "        if(!products.contains(id)){\n"
                + "            containsAll = false;\n"
                + "            break;\n"
                + "        }\n"
                + "    }\n"
                + "    if(containsAll){\n"
                + "        resultStores.add(s)\n"
                + "    }\n"
                + "}\n"
                + "new StoresWithProductsDTO(resultProducts, resultStores)");

        
        List<Store> resultStores = ((StoresWithProductsDTO)scriptResult).getStores();
        assertEquals(expectedStores, resultStores);
    }
}

//script template
//---------------------------------------------
//import net.griddynamics.server.services.SimpleProductService
//import net.griddynamics.api.StoresWithProductsDTO
//import net.griddynamics.storeservice.internal.SimpleStoreService
//
//def storeService = new SimpleStoreService();
//def productService = new SimpleProductService();
//
////---------copy from this point--------------
//
//def resultProducts = []
//def resultStores = []
//def idList = [1,2,3,4,5]
//for(id in idList){
//    tmpProduct = productService.getProductByID(id)
//    if(tmpProduct.id != 0){ 
//        resultProducts.add(tmpProduct)
//    }
//}
//def stores = storeService.getAllStores()
//for(s in stores){
//    def containsAll = true
//    def products = new HashSet(s.getProducts())
//    for(id in idList){
//        if(!products.contains(id)){
//            containsAll = false;
//            break;
//        }
//    }
//    if(containsAll){
//        resultStores.add(s)
//    }
//}
//new StoresWithProductsDTO(resultProducts, resultStores)
