package com.riaboshapka.dao.impl;

import com.riaboshapka.dao.OrderDao;
import com.riaboshapka.domain.Order;

public class OrderDaoImpl implements OrderDao {
    @Override
    public boolean saveOrder(Order order) {
        System.out.println("An order list is creating... Please wait!");
        DataBank.orderMap.put(DataBank.orderCount++, order);
        return true;
    }

    @Override
    public boolean deleteOrder() {
        System.out.println("The order list is deleting... Please wait!");
        return true;
    }

}
