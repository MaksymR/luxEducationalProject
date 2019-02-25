package com.riaboshapka.services;

import com.riaboshapka.domain.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    /**
     * create product
     *
     * @param name of product
     * @param price of product
     */
    void createProduct(String name, BigDecimal price);

    List<Product> getAllProducts();

    /**
     * a method for the admin which modify information about product
     *
     * @param id of product
     * @param productName of product
     * @param productPrice of product
     */
    void modifyProduct(long id, String productName, BigDecimal productPrice);
}
