package com.riaboshapka.services.impl;

import com.riaboshapka.dao.impl.OrderDBDao;
import com.riaboshapka.domain.Client;
import com.riaboshapka.domain.Order;
import com.riaboshapka.domain.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class OrderServiceImplTest {

    private OrderDBDao orderDBDaoForMock = mock(OrderDBDao.class);
    private OrderServiceImpl orderServiceForTest;
    private Client clientForTest;
    private Product productForTest;
    private List<Product> productsListForTest;
    private Order orderForMock;
    private Order orderForList;
    private boolean expectedBooleanResult;
    private long id;

    @Before
    public void setUp() throws Exception {
        orderServiceForTest = new OrderServiceImpl(orderDBDaoForMock);
        clientForTest = new Client("test", "test", 10, "0671231212", "test@test.com");
        productForTest = new Product("apple", BigDecimal.valueOf(20));
        productsListForTest = new ArrayList<>();
        productsListForTest.add(productForTest);
        orderForMock = new Order(clientForTest, productsListForTest);
        orderForList = new Order(clientForTest, productsListForTest);
        expectedBooleanResult = true;
        id = 0L;

    }

    @After
    public void tearDown() throws Exception {
        orderServiceForTest = null;
        clientForTest = null;
        productForTest = null;
        productsListForTest = null;
        orderForMock = null;
        expectedBooleanResult = false;
        id = 0L;

    }

    @Test
    public void createOrder() {
        //GIVEN
        when(orderDBDaoForMock.saveOrder(orderForMock)).thenReturn(expectedBooleanResult);
        //WHEN
        orderServiceForTest.createOrder(clientForTest, productsListForTest);
        //THEN
        verify(orderDBDaoForMock, times(1)).saveOrder(orderForMock);
    }

    @Test
    public void modifyOrder() {
        //GIVEN
        when(orderDBDaoForMock.modifyOrder(id, orderForMock)).thenReturn(expectedBooleanResult);
        //WHEN
        orderServiceForTest.modifyOrder(id, clientForTest, productsListForTest);
        //THEN
        verify(orderDBDaoForMock, times(1)).getAllOrders();
    }

    @Test
    public void getAllOrders() {
        //GIVEN
        List<Order> expectedOrdersList = new ArrayList<>();
        expectedOrdersList.add(orderForList);
        List<Order> ordersListForMock = new ArrayList<>();
        ordersListForMock.add(orderForMock);
        when(orderDBDaoForMock.getAllOrders()).thenReturn(ordersListForMock);
        //WHEN
        orderServiceForTest.getAllOrders();
        //THEN
        assertEquals(expectedOrdersList, ordersListForMock);
        verify(orderDBDaoForMock, times(1)).getAllOrders();
    }

    @Test
    public void delete() {
        //GIVEN
        when(orderDBDaoForMock.deleteOrder(id)).thenReturn(expectedBooleanResult);
        //WHEN
        orderServiceForTest.delete(id);
        //THEN
        verify(orderDBDaoForMock, times(1)).getAllOrders();
    }
}