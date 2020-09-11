package com.riaboshapka.services;

import com.riaboshapka.domain.Client;
import com.riaboshapka.domain.Order;
import com.riaboshapka.domain.Product;

import java.util.List;

public interface OrderService {

    /**
     * create order
     *
     * @param client   for order
     * @param products list of products for order
     */
    void createOrder(Client client, List<Product> products);


    /**
     * get all orders
     *
     * @return list of all orders
     */
    List<Order> getAllOrders();

    /**
     * a method modify information about order
     *
     * @param id                         of order
     * @param clientForModifyOrder       of order
     * @param productsListForModifyOrder list of products for order
     */
    void modifyOrder(long id, Client clientForModifyOrder, List<Product> productsListForModifyOrder);

    /**
     * deleteProduct order by id
     *
     * @param id of order
     */
    void delete(long id);
}
