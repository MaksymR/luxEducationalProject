package com.riaboshapka.validators.impl;

import com.riaboshapka.exceptions.BusinessException;
import com.riaboshapka.validators.ValidationService;

public class ValidationServiceImpl implements ValidationService {

    // дйоргається із слоя сервісів
    @Override
    public void validateAge(int age) throws BusinessException {
        if (age < 0 || age > 200) {
            throw new BusinessException("Incorrect age!!!");
        }
    }
}
