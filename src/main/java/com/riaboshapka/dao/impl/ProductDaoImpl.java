package com.riaboshapka.dao.impl;

import com.riaboshapka.dao.ProductDao;
import com.riaboshapka.domain.Product;

public class ProductDaoImpl implements ProductDao {
    @Override
    public boolean saveProduct(Product product) {
        System.out.println("Saving product... Please wait");
        DataBank.productMap.put(DataBank.productCount++, product);
        return true;
    }

    @Override
    public boolean deleteProduct() {
        System.out.println("Deleting product... Please wait");
        return true;
    }
}
