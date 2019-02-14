package com.riaboshapka.services.impl;

import com.riaboshapka.dao.OrderDao;
import com.riaboshapka.dao.impl.OrderDaoImpl;
import com.riaboshapka.domain.Client;
import com.riaboshapka.domain.Order;
import com.riaboshapka.domain.Product;
import com.riaboshapka.services.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();

    @Override
    public void createOrder(Client client, List<Product> products) {
        Order order = new Order(client, products);
        boolean result = orderDao.saveOrder(client, products);
        if (result) {
            System.out.println("An order list is created: " + order);
        }
    }

    @Override
    public void deleteOrder() {
        boolean result = orderDao.deleteOrder();
        if (result) {
            System.out.println("The order list is deleted.");
        }
    }
}
