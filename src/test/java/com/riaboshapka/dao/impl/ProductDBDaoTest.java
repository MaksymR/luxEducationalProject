package com.riaboshapka.dao.impl;

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

public class ProductDBDaoTest {

    private static final String DB_URL = "jdbc:h2:tcp://localhost/~/ExampleShop";
    private static final String LOGIN = "testForTest";
    private static final String PASSWORD = "testForTest";


    private Connection connection = mock(Connection.class);
    private ProductDBDao productDBDao = mock(ProductDBDao.class);
    private long id;
    private String name;
    private BigDecimal price;
    private boolean expectedBooleanResult;


    @Before
    public void setUp() throws Exception {
        id = 0L;
        name = "apple";
        price = BigDecimal.valueOf(20);
        expectedBooleanResult = true;

    }

    @After
    public void tearDown() throws Exception {
        id = 0L;
        name = "";
        price = BigDecimal.valueOf(0);
        expectedBooleanResult = false;

    }

    @Test(expected = SQLException.class)
    public void saveProduct() throws SQLException {
        //GIVEN
        connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
        Product productForSave = new Product(name, price);
        //WHEN
        boolean productSavingResult = productDBDao.saveProduct(productForSave);
        //THEN
        assertEquals(expectedBooleanResult, productSavingResult);
        connection.close();
    }

    @Test(expected = SQLException.class)
    public void modifyProduct() throws SQLException {
        //GIVEN
        connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
        Product productForModify = new Product(name, price);
        //WHEN
        boolean productModifyingResult = productDBDao.modifyProduct(id, productForModify);
        //THEN
        assertEquals(expectedBooleanResult, productModifyingResult);
        connection.close();
    }

    @Test(expected = SQLException.class)
    public void getAllProducts() throws SQLException {
        //GIVEN
        connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
        Product productForList = new Product(name, price);
        List<Product> expectedProductsList = new ArrayList<>();
        expectedProductsList.add(productForList);
        //WHEN
        List<Product> allProducts = productDBDao.getAllProducts();
        //THEN
        assertEquals(expectedProductsList, allProducts);
        connection.close();
    }

    @Test(expected = SQLException.class)
    public void deleteProduct() throws SQLException {
        //GIVEN
        connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
        //WHEN
        boolean productDeletingResult = productDBDao.deleteProduct(id);
        //THEN
        assertEquals(expectedBooleanResult, productDeletingResult);
        connection.close();
    }
}