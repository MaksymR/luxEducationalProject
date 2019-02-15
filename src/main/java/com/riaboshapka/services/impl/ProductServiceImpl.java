package com.riaboshapka.services.impl;

import com.riaboshapka.dao.ProductDao;
import com.riaboshapka.dao.impl.ProductDaoImpl;
import com.riaboshapka.domain.Product;
import com.riaboshapka.services.ProductService;

import java.math.BigDecimal;

public class ProductServiceImpl implements ProductService {

    private ProductDao productDao = new ProductDaoImpl();

    @Override
    public void createProduct(String name, BigDecimal price) {
        Product product = new Product(name, price);
        boolean result = productDao.saveProduct(product);
        if (result) {
            System.out.println("Product saved: " + product);
        }
    }

    @Override
    public void deleteProduct() {
        boolean resutl = productDao.deleteProduct();
        if (resutl) {
            System.out.println("Product deleted: ");
        }
    }
}
