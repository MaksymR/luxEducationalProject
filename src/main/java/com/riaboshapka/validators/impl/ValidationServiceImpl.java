package com.riaboshapka.validators.impl;

import com.riaboshapka.exceptions.BusinessException;
import com.riaboshapka.validators.ValidationService;

public class ValidationServiceImpl implements ValidationService {

    // taken from the service layer
    @Override
    public void validateAge(int age) throws BusinessException {
        if (age < 0 || age > 200) {
            throw new BusinessException("Incorrect age!!!");
        }
    }

    @Override
    public void validateEmail(String email) throws BusinessException {
        if (!email.contains("@") || !email.contains(".com")) {
            throw new BusinessException("Incorrect email!!! Email has to consist \"@\" and \".com\"");
        }
    }
}
