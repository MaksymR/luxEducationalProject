package com.riaboshapka.services;

import com.riaboshapka.domain.Client;
import com.riaboshapka.domain.Product;

import java.util.List;

public interface OrderService {

    /**
     * create order for client
     *
     * @param client for OrderDao
     * @param products for OrderDao
     */
    void createOrder(Client client, List<Product> products);

    /**
     * delete order
     */
    void deleteOrder();

}
