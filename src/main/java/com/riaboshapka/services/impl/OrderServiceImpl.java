package com.riaboshapka.services.impl;

import com.riaboshapka.dao.OrderDao;
import com.riaboshapka.domain.Client;
import com.riaboshapka.domain.Order;
import com.riaboshapka.domain.Product;
import com.riaboshapka.services.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;

    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public void createOrder(Client client, List<Product> products) {
        Order order = new Order(client, products);
        boolean result = orderDao.saveOrder(order);
        if (result) {
            System.out.println("Product Saved: " + order);
        }
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }
}
