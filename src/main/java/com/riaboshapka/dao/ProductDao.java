package com.riaboshapka.dao;

import com.riaboshapka.domain.Product;

public interface ProductDao {

    /*
    * save product
    * */
    boolean saveProduct(Product product);

    /*
    * deleteProduct
    * */
    boolean deleteProduct();

}
