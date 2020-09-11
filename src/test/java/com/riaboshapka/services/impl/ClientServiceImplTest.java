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
    private long id;
    private String name;
    private String surname;
    private int age;
    private int failAge;
    private String phone;
    private String failPhone;
    private String email;
    private String failEmail;
    private boolean expectedBooleanResult;

    @Before
    public void setUp() throws Exception {
        clientServiceForTest = new ClientServiceImpl(clientDBDaoForTestMock, validationServiceForTestMock);
        id = 0L;
        name = "test";
        surname = "test";
        age = 10;
        failAge = 201;
        phone = "0671231212";
        failPhone = "0601111111";
        email = "test@test.com";
        failEmail = "test.com";
        expectedBooleanResult = true;
    }

    @After
    public void tearDown() throws Exception {
        clientServiceForTest = null;
        id = 0L;
        name = "";
        surname = "";
        age = 0;
        failAge = 0;
        phone = "";
        failPhone = "";
        email = "";
        failEmail = "";
        expectedBooleanResult = false;
    }

    @Test
    public void createClientViaConstructorWithSameArguments() {
        //GIVEN
        Client clientForMock = new Client(name, surname, phone);
        when(clientDBDaoForTestMock.saveClient(clientForMock)).thenReturn(expectedBooleanResult);
        //WHEN
        clientServiceForTest.createClient(name, surname, phone);
        //THEN
        verify(clientDBDaoForTestMock, times(1)).saveClient(clientForMock);
    }

    @Test
    public void createClientViaConstructorWithAllArguments() {
        //GIVEN
        Client clientForMock = new Client(name, surname, age, phone, email);
        when(clientDBDaoForTestMock.saveClient(clientForMock)).thenReturn(expectedBooleanResult);
        //WHEN
        clientServiceForTest.createClient(name, surname, age, phone, email);
        //THEN
        verify(clientDBDaoForTestMock, times(1)).saveClient(clientForMock);
    }

    @Test(expected = BusinessException.class)
    public void createClientExceptionWithAge() throws BusinessException {
        //GIVEN
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
        Client clientForMock = new Client(name, surname, phone);
        when(clientDBDaoForTestMock.modifyClient(id, clientForMock)).thenReturn(expectedBooleanResult);
        //WHEN
        clientServiceForTest.modifyClient(id, name, surname, phone);
        //THEN
        verify(clientDBDaoForTestMock, times(1)).getAllClients();
    }

    @Test
    public void modifyClientViaConstructorWithAllArguments() {
        //GIVEN
        Client clientForMock = new Client(name, surname, age, phone, email);
        when(clientDBDaoForTestMock.modifyClient(id, clientForMock)).thenReturn(expectedBooleanResult);
        //WHEN
        clientServiceForTest.modifyClient(id, name, surname, age, phone, email);
        //THEN
        verify(clientDBDaoForTestMock, times(1)).getAllClients();
    }

    @Test(expected = BusinessException.class)
    public void modifyClientExceptionWithAge() throws BusinessException {
        //GIVEN
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
        Client clientForList = new Client(name, surname, age, phone, email);
        List<Client> expectedClientsList = new ArrayList<>();
        expectedClientsList.add(clientForList);
        Client clientForMock = new Client(name, surname, age, phone, email);
        List<Client> clientsListFromMock = new ArrayList<>();
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
        when(clientDBDaoForTestMock.deleteClient(id)).thenReturn(expectedBooleanResult);
        //WHEN
        clientServiceForTest.deleteClient(id);
        //THEN
        verify(clientDBDaoForTestMock, times(1)).getAllClients();
    }
}