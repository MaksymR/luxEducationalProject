package com.riaboshapka.dao.impl;

import com.riaboshapka.dao.ClientDao;
import com.riaboshapka.dao.ProductDao;
import com.riaboshapka.domain.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDaoImpl implements ProductDao {

    // the object is only in one copy
    private static ProductDao productDao;

    private Map<Long, Product> map = new HashMap<>();
    private static long generator = 0;

    // "singleton"
    private ProductDaoImpl() {
    }

    @Override
    public boolean saveClient(Product product) {
        // saving data (a new client) in database emulation every time
        System.out.println("Saving.... Please wait");
        product.setId(generator++);
        map.put(product.getId(), product);
        return true;
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(map.values());
    }

    // factory methods to create a private constructor, which returns a link to only one object
    public static ProductDao getInstance() {
        if (productDao == null) {
            productDao = new ProductDaoImpl();
        }
        return productDao;
    }
}
