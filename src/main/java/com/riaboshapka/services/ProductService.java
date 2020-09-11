package com.riaboshapka.services;

import com.riaboshapka.domain.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {


    /**
     * create product
     *
     * @param name  of product
     * @param price of product
     */
    void createProduct(String name, BigDecimal price);

    /**
     * get all products
     *
     * @return list of all products
     */
    List<Product> getAllProducts();


    /**
     * a method for the admin which modify information about product
     *
     * @param id           of product
     * @param productName  of product
     * @param productPrice of product
     */
    void modifyProduct(long id, String productName, BigDecimal productPrice);


    /**
     * deleteProduct product
     *
     * @param id of product
     */
    void deleteProduct(long id);
}
