package com.riaboshapka.services.impl;

import com.riaboshapka.dao.impl.ProductDBDao;
import com.riaboshapka.domain.Product;
import com.riaboshapka.services.ProductService;

import java.math.BigDecimal;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductDBDao productDBDao;

    public ProductServiceImpl(ProductDBDao productDBDao) {
        this.productDBDao = productDBDao;
    }

    @Override
    public void createProduct(String productName, BigDecimal price) {
        Product product = new Product(productName, price);
        boolean result = productDBDao.saveProduct(product);
        if (result) {
            System.out.println("Product Saved: " + product);
        }
    }

    @Override
    public void modifyProduct(long id, String productName, BigDecimal productPrice) {
        for (Product product : getAllProducts()) {
            long productId = product.getId();
            if (productId == id) {
                product.setName(productName);
                product.setPrice(productPrice);
                boolean result = productDBDao.modifyProduct(id, product);
                if (result) {
                    System.out.println("Product Saved: " + product);
                }
            }
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return productDBDao.getAllProducts();
    }

    @Override
    public void delete(long id) {
        for (Product product : getAllProducts()) {
            long productId = product.getId();
            if (productId == id) {
                boolean result = productDBDao.deleteProduct(id);
                if (result) {
                    System.out.println("Product Deleted: " + product);
                }
            }
        }
    }
}
