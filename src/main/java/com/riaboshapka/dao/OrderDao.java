package com.riaboshapka.dao;

import com.riaboshapka.domain.Order;

import java.util.ArrayList;

public interface OrderDao {

    /**
     * save order
     *
     * @param order for save
     * @return true is order is saved
     */
    boolean saveOrder(Order order);

    ArrayList<Order> getAllOrders();

    boolean modifyOrder(long id, Order order);
}
