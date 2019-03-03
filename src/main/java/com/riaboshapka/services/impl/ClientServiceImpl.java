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
            validationService.validateEmail(email);
            validationService.validatePhone(phone);
//            List<Client> tempListOfClients = getAllClients();
//            for (Client tempClient : tempListOfClients) {
//                String checkedPhone = tempClient.getPhone();
//                if (checkedPhone.equals(phone)) {
//                    System.out.println("Сan not register because this phone number is already registered");
//                    return;
//                }
//            }
            Client client = new Client(name, surname, age, email, phone);
            boolean result = clientDao.saveClient(client);
            if (result) {
                System.out.println("Client Saved: " + client);
            }
        } catch (BusinessException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void modifyClient(long id, String name, String surname, String phone) {
        this.modifyClient(id, name, surname, 0, phone, null);
    }

    @Override
    public void modifyClient(long id, String name, String surname, int age, String phone, String email) {
        try {
            validationService.validateAge(age);
            validationService.validateEmail(email);
            validationService.validatePhone(phone);
            for (Client client : getAllClients()) {
                long clientId = client.getId();
                if (clientId == id) {
                    client.setName(name);
                    client.setSurname(surname);
                    client.setAge(age);
                    client.setPhone(phone);
                    client.setEmail(email);
                    boolean result = clientDao.modifyClient(id, client);
                    if (result) {
                        System.out.println("Client Saved: " + client);
                    }
                }
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
    public void deleteClient(long id) {
        for (Client client : getAllClients()) {
            long clientId = client.getId();
            if (clientId == id) {
                boolean result = clientDao.deleteClient(id);
                if (result) {
                    System.out.println("Client Deleted: " + client);
                }
            }
        }
    }
}
