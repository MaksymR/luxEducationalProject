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


    /**
     * to read a list of all orders
     *
     * @return list of all products
     */
    ArrayList<Order> getAllOrders();


    /**
     * modify order
     *
     * @param id    of order for save
     * @param order of order for save
     * @return true if order is modified
     */
    boolean modifyOrder(long id, Order order);


    /**
     * delete order by Id
     *
     * @param orderId of order
     * @return true if order was deleted
     */
    boolean deleteOrder(long orderId);
}
