package com.riaboshapka.validators;

import com.riaboshapka.exceptions.BusinessException;

public interface ValidationService {

    /**
     * validate age is from 0 to 150
     *
     * @param age of client
     * @throws BusinessException
     */
    void validateAge(int age) throws BusinessException;

    /**
     * validate email consists "@" and ".com"
     *
     * @param email
     * @throws BusinessException
     */
    void validateEmail(String email) throws BusinessException;

    /**
     * validate phone has to have 10 digit and operators code (067, 097, 050)
     *
     * @param phone
     * @throws BusinessException
     */
    void validatePhone(String phone) throws BusinessException;

}
