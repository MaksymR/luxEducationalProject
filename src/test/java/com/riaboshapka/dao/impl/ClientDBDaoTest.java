package com.riaboshapka.dao.impl;

import com.riaboshapka.domain.Client;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ClientDBDaoTest {

    private static final String DB_URL = "jdbc:h2:tcp://localhost/~/ExampleShop";
    private static final String LOGIN = "testForTest";
    private static final String PASSWORD = "testForTest";


    private Connection connection = mock(Connection.class);
    private ClientDBDao clientDBDao = mock(ClientDBDao.class);
    private long id;
    private String name;
    private String surname;
    private int age;
    private String phone;
    private String email;
    private boolean expectedBooleanResult;


    @Before
    public void setUp() throws Exception {
        id = 0L;
        name = "test";
        surname = "test";
        age = 10;
        phone = "0671231212";
        email = "test@test.com";
        expectedBooleanResult = true;
    }

    @After
    public void tearDown() throws Exception {
        id = 0L;
        name = "";
        surname = "";
        age = 0;
        phone = "0671231212";
        email = "test@test.com";
        expectedBooleanResult = false;
    }

    @Test(expected = SQLException.class)
    public void saveClient() throws SQLException{
        //GIVEN
        connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
        Client clientForSave = new Client(name, surname, age, phone, email);
        //WHEN
        boolean clientSavingResult = clientDBDao.saveClient(clientForSave);
        //THEN
        assertEquals(expectedBooleanResult, clientSavingResult);
        connection.close();
    }

    @Test(expected = SQLException.class)
    public void modifyClient() throws SQLException {
        //GIVEN
        connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
        Client clientForModify = new Client(name, surname, age, phone, email);
        //WHEN
        boolean clientModifyingResult = clientDBDao.modifyClient(id, clientForModify);
        //THEN
        assertEquals(expectedBooleanResult, clientModifyingResult);
        connection.close();
    }

    @Test(expected = SQLException.class)
    public void getAllClients() throws SQLException{
        //GIVEN
        connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
        Client clientForList = new Client(name, surname, age, phone, email);
        List<Client> expectedClientsList = new ArrayList<>();
        expectedClientsList.add(clientForList);
        //WHEN
        List<Client> allClients = clientDBDao.getAllClients();
        //THEN
        assertEquals(expectedClientsList, allClients);
        connection.close();
    }

    @Test(expected = SQLException.class)
    public void deleteClient() throws SQLException{
        //GIVEN
        connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
        //WHEN
        boolean clientDeletingResult = clientDBDao.deleteClient(id);
        //THEN
        assertEquals(expectedBooleanResult, clientDeletingResult);
        connection.close();
    }
}