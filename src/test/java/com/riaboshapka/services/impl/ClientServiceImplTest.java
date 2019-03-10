package com.riaboshapka.services.impl;

import com.riaboshapka.dao.impl.ClientDBDao;
import com.riaboshapka.domain.Client;
import com.riaboshapka.exceptions.BusinessException;
import com.riaboshapka.validators.ValidationService;
import com.riaboshapka.validators.impl.ValidationServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class ClientServiceImplTest {

    private ClientDBDao clientDBDaoForTestMock = mock(ClientDBDao.class);
    private ValidationService validationServiceForTestMock = mock(ValidationService.class);
    private ClientServiceImpl clientServiceForTest;

    @Before
    public void setUp() throws Exception {
        clientServiceForTest = new ClientServiceImpl(clientDBDaoForTestMock, validationServiceForTestMock);
    }

    @After
    public void tearDown() throws Exception {
        clientServiceForTest = null;
    }

    @Test
    public void createClientViaConstructorWithSameArguments() {
        //GIVEN
        String name = "test";
        String surname = "test";
        String phone = "0671231212";
        Client clientForMock = new Client(name, surname, phone);
        boolean expectedBooleanResult = true;
        when(clientDBDaoForTestMock.saveClient(clientForMock)).thenReturn(expectedBooleanResult);
        //WHEN
        clientServiceForTest.createClient(name, surname, phone);
        //THEN
        verify(clientDBDaoForTestMock, times(1)).saveClient(clientForMock);
    }

    @Test
    public void createClientViaConstructorWithAllArguments() {
        //GIVEN
        String name = "test";
        String surname = "test";
        int age = 10;
        String phone = "0671231212";
        String email = "test@test.com";
        Client clientForMock = new Client(name, surname, age, phone, email);
        boolean expectedBooleanResult = true;
        when(clientDBDaoForTestMock.saveClient(clientForMock)).thenReturn(expectedBooleanResult);
        //WHEN
        clientServiceForTest.createClient(name, surname, age, phone, email);
        //THEN
        verify(clientDBDaoForTestMock, times(1)).saveClient(clientForMock);
    }

    @Test(expected = BusinessException.class)
    public void createClientExceptionWithAge() throws BusinessException {
        //GIVEN
        String name = "test";
        String surname = "test";
        int age = 10;
        String phone = "0671231212";
        String email = "test@test.com";
        int failAge = 201;
        ValidationService validationService = new ValidationServiceImpl();
        validationService.validateAge(failAge);
        //WHEN
        clientServiceForTest.createClient(name, surname, age, phone, email);
        //THEN
        verify(validationServiceForTestMock, times(1)).validateAge(failAge);
    }

    @Test(expected = BusinessException.class)
    public void createClientExceptionWithEmail() throws BusinessException {
        //GIVEN
        String name = "test";
        String surname = "test";
        int age = 10;
        String phone = "0671231212";
        String email = "test@test.com";
        String failEmail = "test.com";
        ValidationService validationService = new ValidationServiceImpl();
        validationService.validateEmail(failEmail);
        //WHEN
        clientServiceForTest.createClient(name, surname, age, phone, email);
        //THEN
        verify(validationServiceForTestMock, times(1)).validateEmail(failEmail);
    }

    @Test(expected = BusinessException.class)
    public void createClientExceptionWithPhone() throws BusinessException {
        //GIVEN
        String name = "test";
        String surname = "test";
        int age = 10;
        String phone = "0671231212";
        String email = "test@test.com";
        String failPhone = "060_111_11_11";
        ValidationService validationService = new ValidationServiceImpl();
        validationService.validatePhone(failPhone);
        //WHEN
        clientServiceForTest.createClient(name, surname, age, phone, email);
        //THEN
        verify(validationServiceForTestMock, times(1)).validatePhone(failPhone);
    }


    @Test
    public void modifyClientWithSameArguments() {
        //GIVEN
        long id = 0L;
        String name = "test";
        String surname = "test";
        String phone = "0671231212";
        Client clientForMock = new Client(name, surname, phone);
        boolean expectedBooleanResult = true;
        when(clientDBDaoForTestMock.modifyClient(id, clientForMock)).thenReturn(expectedBooleanResult);
        //WHEN
        clientServiceForTest.modifyClient(id, name, surname, phone);
        //THEN
        verify(clientDBDaoForTestMock, times(1)).getAllClients();
    }

    @Test
    public void modifyClientViaConstructorWithAllArguments() {
        //GIVEN
        long id = 0L;
        String name = "test";
        String surname = "test";
        int age = 10;
        String phone = "0671231212";
        String email = "test@test.com";
        Client clientForMock = new Client(name, surname, age, phone, email);
        boolean expectedBooleanResult = true;
        when(clientDBDaoForTestMock.modifyClient(id, clientForMock)).thenReturn(expectedBooleanResult);
        //WHEN
        clientServiceForTest.modifyClient(id, name, surname, age, phone, email);
        //THEN
        verify(clientDBDaoForTestMock, times(1)).getAllClients();
    }

    @Test(expected = BusinessException.class)
    public void modifyClientExceptionWithAge() throws BusinessException {
        //GIVEN
        long id = 0L;
        String name = "test";
        String surname = "test";
        int age = 10;
        String phone = "0671231212";
        String email = "test@test.com";
        int failAge = 201;
        ValidationService validationService = new ValidationServiceImpl();
        validationService.validateAge(failAge);
        //WHEN
        clientServiceForTest.modifyClient(id, name, surname, age, phone, email);
        //THEN
        verify(validationServiceForTestMock, times(1)).validateAge(failAge);
    }

    @Test(expected = BusinessException.class)
    public void modifyClientExceptionWithEmail() throws BusinessException {
        //GIVEN
        long id = 0L;
        String name = "test";
        String surname = "test";
        int age = 10;
        String phone = "0671231212";
        String email = "test@test.com";
        String failEmail = "test.com";
        ValidationService validationService = new ValidationServiceImpl();
        validationService.validateEmail(failEmail);
        //WHEN
        clientServiceForTest.modifyClient(id, name, surname, age, phone, email);
        //THEN
        verify(validationServiceForTestMock, times(1)).validateEmail(failEmail);
    }

    @Test(expected = BusinessException.class)
    public void modifyClientExceptionWithPhone() throws BusinessException {
        //GIVEN
        long id = 0L;
        String name = "test";
        String surname = "test";
        int age = 10;
        String phone = "0671231212";
        String email = "test@test.com";
        String failPhone = "060_111_11_11";
        ValidationService validationService = new ValidationServiceImpl();
        validationService.validatePhone(failPhone);
        //WHEN
        clientServiceForTest.modifyClient(id, name, surname, age, phone, email);
        //THEN
        verify(validationServiceForTestMock, times(1)).validatePhone(failPhone);
    }

    @Test
    public void getAllClients() {
        //GIVEN
        String name = "test";
        String surname = "test";
        int age = 10;
        String phone = "0671231212";
        String email = "test@test.com";
        Client clientForList = new Client(name, surname, age, phone, email);
        List<Client> expectedClientsList = new ArrayList<>();
        expectedClientsList.add(clientForList);
        List<Client> clientsListFromMock = new ArrayList<>();
        Client clientForMock = new Client(name, surname, age, phone, email);
        clientsListFromMock.add(clientForMock);
        when(clientDBDaoForTestMock.getAllClients()).thenReturn(clientsListFromMock);
        //WHEN
        List<Client> clientsList = clientServiceForTest.getAllClients();
        //THEN
        assertEquals(expectedClientsList, clientsList);
        verify(clientDBDaoForTestMock, times(1)).getAllClients();
    }

    @Test
    public void deleteClient() {
        //GIVEN
        long id = 0L;
        boolean expectedBooleanResult = true;
        when(clientDBDaoForTestMock.deleteClient(id)).thenReturn(expectedBooleanResult);
        //WHEN
        clientServiceForTest.deleteClient(id);
        //THEN
        verify(clientDBDaoForTestMock, times(1)).getAllClients();
    }
}