package com.riaboshapka.validators.impl;

import com.riaboshapka.exceptions.BusinessException;
import com.riaboshapka.validators.ValidationService;

import java.util.ArrayList;

public class ValidationServiceImpl implements ValidationService {

    private static final String CODE_KIYVSTAR_067 = "067";
    private static final String CODE_KIYVSTAR_097 = "097";
    private static final String CODE_VODAFONE_050 = "050";
    private static final int INITIAL_AGE = 0;
    private static final int AGE_LIMITATION = 200;


    // taken from the service layer
    @Override
    public void validateAge(int age) throws BusinessException {
        if (age < INITIAL_AGE || age > AGE_LIMITATION) {
            throw new BusinessException("Incorrect age!!! Enter age from 0 to 200 years");
        }
    }

    @Override
    public void validateEmail(String email) throws BusinessException {
        // this check for method createClient(String name, String surname, String phone)
        // from ClientServiceImpl because there is a default value "null" of an email in the method's body
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
        //System.out.println(mobileCode);
        ArrayList<String> operatorsCodes = new ArrayList<>();
        operatorsCodes.add(CODE_KIYVSTAR_067);
        operatorsCodes.add(CODE_KIYVSTAR_097);
        operatorsCodes.add(CODE_VODAFONE_050);
        if (phone.length() != 10) {
            throw new BusinessException("Incorrect length of phone!!!" +
                    " The length of phone has to have 10 digits");
        }
        if (!operatorsCodes.contains(mobileCode)) {
            throw new BusinessException("Incorrect mobile operator!!!" +
                    " Mobile operator has to have next code \"067\" or \"097\" or \"050\"");
        }
    }

}
