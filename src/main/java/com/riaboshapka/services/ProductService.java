package com.riaboshapka.services;

import com.riaboshapka.domain.Product;

import java.math.BigDecimal;

public interface ProductService {

    /**
     * create product
     *
     * @param name for ProductDao
     * @param price for ProductDao
     */
    void createProduct(String name, BigDecimal price);


    /**
     * delete product
     */
    void deleteProduct();

}
