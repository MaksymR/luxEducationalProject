package com.riaboshapka.dao.impl;

import com.riaboshapka.dao.OrderDao;
import com.riaboshapka.domain.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
Never used after OrderDBDao.java was created
 */

public class OrderDaoImpl implements OrderDao {

    private static OrderDao orderDao;
    private Map<Long, Order> map = new HashMap<>();
    private static long generator = 0;

    private OrderDaoImpl() {
    }

    @Override
    public boolean saveOrder(Order order) {
        System.out.println("Saving.... Please wait");
        order.setId(generator++);
        map.put(order.getId(), order);
        return true;
    }

    @Override
    public ArrayList<Order> getAllOrders() {
        return new ArrayList<>(map.values());
    }

    @Override
    public boolean modifyOrder(long id, Order newOrder) {
        System.out.println("Modifying.... Please wait");
        Order oldOrder = map.get(id);
        map.replace(id, oldOrder, newOrder);
        return true;
    }

    @Override
    public boolean deleteOrder(long orderId) {
        System.out.println("Deleting... Please wait");
        map.remove(orderId);
        return true;
    }

    public static OrderDao getInstance() {
        if (orderDao == null) {
            orderDao = new OrderDaoImpl();
        }
        return orderDao;
    }
}
