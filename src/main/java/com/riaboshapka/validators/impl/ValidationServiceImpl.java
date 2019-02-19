package com.riaboshapka.validators.impl;

import com.riaboshapka.exceptions.BusinessException;
import com.riaboshapka.validators.ValidationService;

public class ValidationServiceImpl implements ValidationService {

    // taken from the service layer
    @Override
    public void validateAge(int age) throws BusinessException {
        if (age < 0 || age > 200) {
            throw new BusinessException("Incorrect age!!! Enter age from 0 to 200 years");
        }
    }

    @Override
    public void validateEmail(String email) throws BusinessException {
        // this check for method createClient(String name, String surname, String phone)
        // from ClientServiceImpl because there is a default value "null" of an email in the method's body
//        if (email.contains(null)) {
//            return;
//        }
//        String nullEmail = null;
        if (email == null) {
            return;
        }
        if (!email.contains("@") || !email.contains(".com")) {
            throw new BusinessException("Incorrect email!!! Email has to consist \"@\" and \".com\"");
        }
    }

    @Override
    public void validatePhone(String phone) throws BusinessException {
        String mobileCode = phone.substring(0, 3);
        System.out.println(mobileCode);
        String KyivStarCode1 = "067";
        String KyivStarCode2 = "097";
        String VodafoneCode1 = "050";
        if (phone.length() != 10) {
            throw new BusinessException("Incorect length of phone!!!" +
                    " The length of phone has to have 10 digits");
        }
        if (!mobileCode.equals(KyivStarCode1) && !mobileCode.equals(KyivStarCode2) && !mobileCode.equals(VodafoneCode1)) {
            throw new BusinessException("Incorect mobile operator!!!" +
                    " Mobile operator has to have next code \"067\" or \"097\" or \"050\"");
        }
    }

}
