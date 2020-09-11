package com.riaboshapka.dao.impl;

import com.riaboshapka.domain.Client;
import com.riaboshapka.domain.Order;
import com.riaboshapka.domain.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class OrderDBDaoTest {

    private static final String DB_URL = "jdbc:h2:tcp://localhost/~/ExampleShop";
    private static final String LOGIN = "testForTest";
    private static final String PASSWORD = "testForTest";


    private Connection connection = mock(Connection.class);
    private OrderDBDao orderDBDao = mock(OrderDBDao.class);
    private long id;
    private Client clientForTest;
    private Product productForTest;
    private List<Product> productsListForTest;
    private boolean expectedBooleanResult;


    @Before
    public void setUp() throws Exception {
        id = 0L;
        clientForTest = new Client("test", "test", 10, "0671231212", "test@test.com");
        productForTest = new Product("apple", BigDecimal.valueOf(20));
        productsListForTest = new ArrayList<>();
        productsListForTest.add(productForTest);
        expectedBooleanResult = true;
    }

    @After
    public void tearDown() throws Exception {
        id = 0L;
        clientForTest = null;
        productForTest = null;
        productsListForTest = null;
        expectedBooleanResult = false;

    }

    @Test(expected = SQLException.class)
    public void saveOrder() throws SQLException {
        //GIVEN
        connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
        Order orderForSave = new Order(clientForTest, productsListForTest);
        //WHEN
        boolean orderSavingResult = orderDBDao.saveOrder(orderForSave);
        //THEN
        assertEquals(expectedBooleanResult, orderSavingResult);
        connection.close();
    }

    @Test(expected = SQLException.class)
    public void modifyOrder() throws SQLException {
        //GIVEN
        connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
        Order orderForModify = new Order(clientForTest, productsListForTest);
        //WHEN
        boolean orderModifyingResult = orderDBDao.modifyOrder(id, orderForModify);
        //THEN
        assertEquals(expectedBooleanResult, orderModifyingResult);
        connection.close();
    }

    @Test(expected = SQLException.class)
    public void getAllOrders() throws SQLException {
        //GIVEN
        connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
        Order orderForList = new Order(clientForTest, productsListForTest);
        List<Order> expectedOrdersList = new ArrayList<>();
        expectedOrdersList.add(orderForList);
        //WHEN
        List<Order> allOrders = orderDBDao.getAllOrders();
        //THEN
        assertEquals(expectedOrdersList, allOrders);
        connection.close();
    }

    @Test(expected = SQLException.class)
    public void deleteOrder() throws SQLException {
        //GIVEN
        connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
        //WHEN
        boolean orderDeletingResult = orderDBDao.deleteOrder(id);
        //THEN
        assertEquals(expectedBooleanResult, orderDeletingResult);
        connection.close();
    }
}