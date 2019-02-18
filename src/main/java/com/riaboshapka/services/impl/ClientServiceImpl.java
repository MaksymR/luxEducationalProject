package com.riaboshapka.services.impl;

import com.riaboshapka.dao.ClientDao;
import com.riaboshapka.domain.Client;
import com.riaboshapka.exceptions.BusinessException;
import com.riaboshapka.services.ClientService;
import com.riaboshapka.validators.ValidationService;

import java.util.List;

public class ClientServiceImpl implements ClientService {

    // депенденсі інжекшин
    // це залежність для конструктора
    private ClientDao clientDao;
    private ValidationService validationService;

    public ClientServiceImpl(ClientDao clientDao, ValidationService validationService) {
        this.clientDao = clientDao;
        this.validationService = validationService;
    }

    @Override
    public void createClient(String name, String surname, String phone) {
        // викликаємо більший метод для попердження дупліката логіки
        this.createClient(name, surname, 0, phone, null);
    }

    @Override
    public void createClient(String name, String surname, int age, String phone, String email) {
        try {
            validationService.validateAge(age);
            // якщо вік не вірного формату то більше нічого не створюється
            Client client = new Client(name, surname, age, email, phone);
            boolean result = clientDao.saveClient(client);
            if (result) {
                System.out.println("Client Saved: " + client);
            }
        } catch (BusinessException ex) {
            ex.printStackTrace();
        }
    }

    // отримуємо копію клієнтів
    @Override
    public List<Client> getAllClients() {
        return clientDao.getAllClients();
    }


    @Override
    public void deleteClient() {

    }
}
