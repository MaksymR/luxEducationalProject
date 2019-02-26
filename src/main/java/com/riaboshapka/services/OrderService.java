package com.riaboshapka.services;

import com.riaboshapka.domain.Client;
import com.riaboshapka.domain.Order;
import com.riaboshapka.domain.Product;

import java.util.List;

public interface OrderService {

    void createOrder(Client client, List<Product> products);

    List<Order> getAllOrders();

    void modifyOrder(long id, Client clientForModifyOrder, List<Product> productsListForModifyOrder);
}
