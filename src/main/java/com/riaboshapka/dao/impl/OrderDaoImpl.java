package com.riaboshapka.dao.impl;

import com.riaboshapka.dao.OrderDao;
import com.riaboshapka.domain.Client;
import com.riaboshapka.domain.Product;

import java.util.List;

public class OrderDaoImpl implements OrderDao {
    @Override
    public boolean saveOrder(Client client, List<Product> products) {
        System.out.println("An order list is creating... Please wait!");
        return true;
    }

    @Override
    public boolean deleteOrder() {
        System.out.println("The order list is deleting... Please wait!");
        return true;
    }

}
