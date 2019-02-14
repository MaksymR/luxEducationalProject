package com.riaboshapka.services;


import java.math.BigDecimal;

public interface ProductService {

    /**
     * create product
     *
     * @param productId for ProductDao
     * @param name      for ProductDao
     * @param price     for ProductDao
     */
    void createProduct(Long productId, String name, BigDecimal price);


    /**
     * delete product
     */
    void deleteProduct();

}
