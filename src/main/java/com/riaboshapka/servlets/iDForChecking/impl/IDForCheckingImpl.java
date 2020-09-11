package com.riaboshapka.servlets.iDForChecking.impl;

import com.riaboshapka.dao.impl.ClientDBDao;
import com.riaboshapka.dao.impl.OrderDBDao;
import com.riaboshapka.dao.impl.ProductDBDao;
import com.riaboshapka.domain.Client;
import com.riaboshapka.domain.Order;
import com.riaboshapka.domain.Product;
import com.riaboshapka.servlets.iDForChecking.IDForChecking;

import java.util.ArrayList;
import java.util.List;

public class IDForCheckingImpl implements IDForChecking {

    // prepare for checking order's ID into BD
    @Override
    public List<Long> getOrderIDForChecking() {
        OrderDBDao orderDBDao = new OrderDBDao();
        List<Order> orderList = orderDBDao.getAllOrders();
        List<Long> orderIDList = new ArrayList<>();
        for (Order order : orderList) {
            orderIDList.add(order.getId());
        }
        return orderIDList;
    }

    // prepare for checking client's ID into BD
    @Override
    public List<Long> getClientIDForChecking() {
        ClientDBDao clientDBDao = new ClientDBDao();
        List<Client> clientList = clientDBDao.getAllClients();
        List<Long> clientIDList = new ArrayList<>();
        for (Client client : clientList) {
            clientIDList.add(client.getId());
        }
        return clientIDList;
    }

    // prepare for checking product's ID into BD
    @Override
    public List<Long> getProductIDForChecking() {
        ProductDBDao productDBDao = new ProductDBDao();
        List<Product> productList = productDBDao.getAllProducts();
        List<Long> productIDList = new ArrayList<>();
        for (Product product : productList) {
            productIDList.add(product.getId());
        }
        return productIDList;
    }

}
