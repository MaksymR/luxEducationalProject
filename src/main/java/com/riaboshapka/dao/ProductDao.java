package com.riaboshapka.dao;

import com.riaboshapka.domain.Product;

import java.util.List;

public interface ProductDao {

    /**
     * save product
     *
     * @param product for save
     * @return true if client is saved
     */
    boolean saveClient(Product product);


    /**
     * to read a list of all products
     *
     * @return list of all products
     */
    List<Product> getAllProducts();


    /**
     * modify product
     *
     * @param id      of product for save
     * @param product of product for save
     * @return true if product is modified
     */
    boolean modifyProduct(long id, Product product);


    /**
     * delete product by Id
     *
     * @param id of product
     * @return true if product was deleted
     */
    boolean deleteProduct(long id);
}
