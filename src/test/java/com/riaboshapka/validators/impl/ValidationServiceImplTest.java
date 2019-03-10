package com.riaboshapka.validators.impl;

import com.riaboshapka.exceptions.BusinessException;
import com.riaboshapka.validators.ValidationService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValidationServiceImplTest {

    private ValidationService validationService;

    @Before
    public void setUp() throws Exception {
        validationService = new ValidationServiceImpl();
    }

    @After
    public void tearDown() throws Exception {
        validationService = null;
    }

    @Test(expected = BusinessException.class)
    public void validateAge() throws BusinessException {
        //GIVEN
        int failAge = 201;
        //WHEN
        validationService.validateAge(failAge);
        //THEN
    }

    @Test(expected = BusinessException.class)
    public void validateEmail() throws BusinessException{
        //GIVEN
        String failEmail = "test.com";
        //WHEN
        validationService.validateEmail(failEmail);
        //THEN
    }

    @Test(expected = BusinessException.class)
    public void validatePhone() throws BusinessException{
        //GIVEN
        String failPhone = "0601111111";
        //WHEN
        validationService.validatePhone(failPhone);
        //THEN
    }
}