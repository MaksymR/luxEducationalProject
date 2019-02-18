package com.riaboshapka.validators;

import com.riaboshapka.exceptions.BusinessException;

public interface ValidationService {

    // валідує уведений вік від 0 до 150
    void validateAge(int age) throws BusinessException;

}
