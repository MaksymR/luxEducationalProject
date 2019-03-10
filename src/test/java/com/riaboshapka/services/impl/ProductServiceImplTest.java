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

    @Before
    public void setUp() throws Exception {
        productServiceForTest = new ProductServiceImpl(productDBDaoForMock);
    }

    @After
    public void tearDown() throws Exception {
        productServiceForTest = null;
    }

    @Test
    public void createProduct() {
        //GIVEN
        String name = "apple";
        BigDecimal price = BigDecimal.valueOf(20);
        Product productForMock = new Product(name, price);
        boolean expectedBooleanResult = true;
        when(productDBDaoForMock.saveProduct(productForMock)).thenReturn(expectedBooleanResult);
        //WHEN
        productServiceForTest.createProduct(name, price);
        //THEN
        verify(productDBDaoForMock, times(1)).saveProduct(productForMock);
    }

    @Test
    public void modifyProduct() {
        //GIVEN
        long id = 0L;
        String name = "apple";
        BigDecimal price = BigDecimal.valueOf(20);
        Product productForMock = new Product(name, price);
        boolean expectedBooleanResult = true;
        when(productDBDaoForMock.modifyProduct(id, productForMock)).thenReturn(expectedBooleanResult);
        //WHEN
        productServiceForTest.modifyProduct(id, name, price);
        //THEN
        verify(productDBDaoForMock, times(1)).getAllProducts();
    }

    @Test
    public void getAllProducts() {
        //GIVEN
        String name = "apple";
        BigDecimal price = BigDecimal.valueOf(20);
        Product productForList = new Product(name, price);
        List<Product> expectedProductsList = new ArrayList<>();
        expectedProductsList.add(productForList);
        Product productForMock = new Product(name, price);
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
        long id = 0L;
        boolean expectedBooleanResult = true;
        when(productDBDaoForMock.deleteProduct(id)).thenReturn(expectedBooleanResult);
        //WHEN
        productServiceForTest.delete(id);
        //THEN
        verify(productDBDaoForMock, times(1)).getAllProducts();
    }
}