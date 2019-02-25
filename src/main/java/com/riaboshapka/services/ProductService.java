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
}
