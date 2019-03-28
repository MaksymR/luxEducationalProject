package com.riaboshapka.services.impl;

import com.riaboshapka.dao.impl.ProductDBDao;
import com.riaboshapka.domain.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {

    private ProductDBDao productDBDaoForMock = mock(ProductDBDao.class);
    private ProductServiceImpl productServiceForTest;
    private long id;
    private String name;
    private BigDecimal price;
    private Product productForMock;
    private Product productForList;
    private boolean expectedBooleanResult;

    @Before
    public void setUp() throws Exception {
        productServiceForTest = new ProductServiceImpl(productDBDaoForMock);
        id = 0L;
        name = "apple";
        price = BigDecimal.valueOf(20);
        productForMock = new Product(name, price);
        productForList = new Product(name, price);
        expectedBooleanResult = true;
    }

    @After
    public void tearDown() throws Exception {
        productServiceForTest = null;
        id = 0L;
        name = "";
        price = BigDecimal.valueOf(0);
        productForMock = null;
        expectedBooleanResult = false;

    }

    @Test
    public void createProduct() {
        //GIVEN
        when(productDBDaoForMock.saveProduct(productForMock)).thenReturn(expectedBooleanResult);
        //WHEN
        productServiceForTest.createProduct(name, price);
        //THEN
        verify(productDBDaoForMock, times(1)).saveProduct(productForMock);
    }

    @Test
    public void modifyProduct() {
        //GIVEN
        when(productDBDaoForMock.modifyProduct(id, productForMock)).thenReturn(expectedBooleanResult);
        //WHEN
        productServiceForTest.modifyProduct(id, name, price);
        //THEN
        verify(productDBDaoForMock, times(1)).getAllProducts();
    }

    @Test
    public void getAllProducts() {
        //GIVEN
        List<Product> expectedProductsList = new ArrayList<>();
        expectedProductsList.add(productForList);
        List<Product> productsListForMock = new ArrayList<>();
        productsListForMock.add(productForMock);
        when(productDBDaoForMock.getAllProducts()).thenReturn(productsListForMock);
        //WHEN
        List<Product> productsList = productServiceForTest.getAllProducts();
        //THEN
        assertEquals(expectedProductsList, productsList);
        verify(productDBDaoForMock, times(1)).getAllProducts();
    }

    @Test
    public void delete() {
        //GIVEN
        when(productDBDaoForMock.deleteProduct(id)).thenReturn(expectedBooleanResult);
        //WHEN
        productServiceForTest.deleteProduct(id);
        //THEN
        verify(productDBDaoForMock, times(1)).getAllProducts();
    }
}