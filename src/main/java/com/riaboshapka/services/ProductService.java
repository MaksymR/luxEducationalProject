package com.riaboshapka.services;

import com.riaboshapka.domain.Product;

import java.math.BigDecimal;

public interface ProductService {

    /*
    * create product
    * */
    void createProduct(String name, BigDecimal price);

    /*
    * delete product
    * */
    void deleteProduct();

}
