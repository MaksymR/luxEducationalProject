package com.riaboshapka.services.impl;

import com.riaboshapka.dao.ClientDao;
import com.riaboshapka.domain.Client;
import com.riaboshapka.exceptions.BusinessException;
import com.riaboshapka.services.ClientService;
import com.riaboshapka.validators.ValidationService;

import java.util.List;

public class ClientServiceImpl implements ClientService {

    // the dependency injection
    // this dependency for constructor
    private ClientDao clientDao;
    private ValidationService validationService;

    public ClientServiceImpl(ClientDao clientDao, ValidationService validationService) {
        this.clientDao = clientDao;
        this.validationService = validationService;
    }

    @Override
    public void createClient(String name, String surname, String phone) {
        // call the larger method for passing the duplicate of logic
        // email temporarily doesn't work
        this.createClient(name, surname, 0, phone, null);
    }

    @Override
    public void createClient(String name, String surname, int age, String phone, String email) {
        try {
            // If the age is not correct, then nothing else is created
            validationService.validateAge(age);
            // ignore email ("null") if registration is from ClientMenu
//            if (email != null) {
//                validationService.validateEmail(email);
//            }
            validationService.validateEmail(email);
            validationService.validatePhone(phone);
            List<Client> tempListOfClients = getAllClients();
            for (int i = 0; i < tempListOfClients.size(); i++) {
                Client tempClient = tempListOfClients.get(i);
                String checkedPhone = tempClient.getPhone();
                if (checkedPhone.equals(phone)) {
                    System.out.println("Сan not register because this phone number is already registered");
                    return;
                }
            }
            Client client = new Client(name, surname, age, email, phone);
            boolean result = clientDao.saveClient(client);
            if (result) {
                System.out.println("Client Saved: " + client);
            }
        } catch (BusinessException ex) {
            ex.printStackTrace();
        }
    }

    // get a copy of the clients
    @Override
    public List<Client> getAllClients() {
        return clientDao.getAllClients();
    }


    @Override
    public void deleteClient() {

    }
}
