package com.riaboshapka.services;

import com.riaboshapka.domain.Client;
import com.riaboshapka.domain.Product;

import java.util.List;

public interface OrderService {

    /**
     * create an order
     *
     * @param client   for create the order
     * @param products for create the order
     */
    void createOrder(Client client, List<Product> products);

    /**
     * delete order
     */
    void deleteOrder();

}
