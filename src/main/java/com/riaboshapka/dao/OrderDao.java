package com.riaboshapka.dao;

import com.riaboshapka.domain.Client;
import com.riaboshapka.domain.Product;

import java.util.List;

public interface OrderDao {

    /**
     * @param client from DB
     * @param products from DB
     * @return true if order is created and false in other case
     */
    boolean saveOrder(Client client, List<Product> products);

    /**
     * @return true if order is deleted and false in other case
     */
    boolean deleteOrder();

}
