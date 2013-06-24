/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.griddynamics.storeservice.internal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import net.griddynamics.storeservice.Store;
import net.griddynamics.storeservice.StoreService;

/**
 *
 * @author one
 */
public class SimpleStoreService implements StoreService {

    private static final Set<Store> stores = new HashSet<Store>();
    private static final Store NOT_FOUND = new Store(0, "Not Found!", "None", Collections.EMPTY_SET);

    public SimpleStoreService() {
        init();
    }

    private void init() {
        InputStream storeStream = getClass().getResourceAsStream("/stores.txt");
        
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(storeStream));
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                String[] res = line.split(":");

                int id = Integer.parseInt(res[0].trim());
                String name = res[1].trim();
                String location = res[2].trim();
                HashSet<Integer> products = new HashSet(res.length - 3);
                for (int i = 3; i < res.length; i++) {
                    products.add(Integer.valueOf(res[i].trim()));
                }
                Store store = new Store(id, name, location, products);
                stores.add(store);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Store getStoreByID(int id) {
        for (Store s : stores) {
            if (s.getId() == id) {
                return s;
            }
        }
        return NOT_FOUND;
    }

    @Override
    public Set<Store> getAllStores() {
        if (stores == null) {
            return Collections.EMPTY_SET;
        }
        return new HashSet<Store>(stores);
    }
}
