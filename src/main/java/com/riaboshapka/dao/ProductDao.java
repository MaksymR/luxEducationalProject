package com.riaboshapka.dao;

import com.riaboshapka.domain.Product;

import java.util.List;

public interface ProductDao {

    /**
     * @param product for DB
     * @return true if product is saved and false in other case
     */
    boolean saveProduct(Product product);

    /**
     * @return true is product is deleted and false in other case
     */
    boolean deleteProduct();

}
