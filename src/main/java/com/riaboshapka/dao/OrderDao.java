package com.riaboshapka.dao;

import com.riaboshapka.domain.Order;

public interface OrderDao {

    /**
     * save order
     *
     * @param order for save
     * @return true if order is saved and false in other case
     */
    boolean saveOrder(Order order);

    /**
     * @return true if order is deleted and false in other case
     */
    boolean deleteOrder();

}
