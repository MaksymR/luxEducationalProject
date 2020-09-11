package com.riaboshapka.validators;

import com.riaboshapka.exceptions.BusinessException;

public interface ValidationService {

    /**
     * validate age is from 0 to 150
     *
     * @param age of client
     * @throws BusinessException for validate age
     */
    void validateAge(int age) throws BusinessException;


    /**
     * validate email consists "@" and ".com"
     *
     * @param email of client
     * @throws BusinessException for validate email
     */
    void validateEmail(String email) throws BusinessException;


    /**
     * validate phone has to have 10 digit and operators code (067, 097, 050)
     *
     * @param phone of client
     * @throws BusinessException for validate phone
     */
    void validatePhone(String phone) throws BusinessException;

}
