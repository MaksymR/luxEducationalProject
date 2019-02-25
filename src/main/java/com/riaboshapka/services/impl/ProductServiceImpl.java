package com.riaboshapka.services.impl;

import com.riaboshapka.dao.ProductDao;
import com.riaboshapka.domain.Product;
import com.riaboshapka.services.ProductService;

import java.math.BigDecimal;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public void createProduct(String productName, BigDecimal price) {
        Product product = new Product(productName, price);
        boolean result = productDao.saveClient(product);
        if (result) {
            System.out.println("Product Saved: " + product);
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    @Override
    public void modifyProduct(long id, String productName, BigDecimal productPrice) {
        for (Product product : getAllProducts()) {
            long productId = product.getId();
            if (productId == id) {
                product.setName(productName);
                product.setPrice(productPrice);
                boolean result = productDao.modifyProduct(id, product);
                if (result) {
                    System.out.println("Product Saved: " + product);
                }
            }
        }
    }
}
