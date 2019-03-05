package com.riaboshapka.services.impl;

import com.riaboshapka.dao.impl.OrderDBDao;
import com.riaboshapka.domain.Client;
import com.riaboshapka.domain.Order;
import com.riaboshapka.domain.Product;
import com.riaboshapka.services.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderDBDao orderDBDao;

    public OrderServiceImpl(OrderDBDao orderDBDao) {
        this.orderDBDao = orderDBDao;
    }

    @Override
    public void createOrder(Client client, List<Product> products) {
        Order order = new Order(client, products);
        boolean result = orderDBDao.saveOrder(order);
        if (result) {
            System.out.println("Product Saved: " + order);
        }
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDBDao.getAllOrders();
    }

    @Override
    public void modifyOrder(long id, Client clientForModifyOrder, List<Product> productsListForModifyOrder) {
        for (Order order : getAllOrders()) {
            long orderId = order.getId();
            if (orderId == id) {
                order.setClient(clientForModifyOrder);
                order.setProducts(productsListForModifyOrder);
                boolean result = orderDBDao.modifyOrder(id, order);
                if (result) {
                    System.out.println("Order Saved: " + order);
                }
            }
        }
    }

    @Override
    public void delete(long id) {
        for (Order order : getAllOrders()) {
            long orderId = order.getId();
            if (orderId == id) {
                boolean result = orderDBDao.deleteOrder(id);
                if (result) {
                    System.out.println("Order Deleted: " + order);
                }
            }
        }
    }
}
