package com.riaboshapka.dao.impl;

import com.riaboshapka.domain.Client;
import com.riaboshapka.domain.Order;
import com.riaboshapka.domain.Product;

import java.util.HashMap;
import java.util.Map;

public class DataBank {
    public static Long clientCount = 0L;
    public static Long productCount = 0L;
    public static Long orderCount = 0L;
    public static Map<Long, Client> clientMap = new HashMap<>();
    public static Map<Long, Product> productMap = new HashMap<>();
    public static Map<Long, Order> orderMap = new HashMap<>();



}
