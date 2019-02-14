package com.riaboshapka.services.impl;

import com.riaboshapka.dao.ClientDao;
import com.riaboshapka.dao.impl.ClientDaoImpl;
import com.riaboshapka.domain.Client;
import com.riaboshapka.services.ClientService;

public class ClientServiceImpl implements ClientService {

    private ClientDao clientDao = new ClientDaoImpl();

    @Override
    public void createClient(long clientsId, String name, String surname, String phone) {
        Client client = new Client(clientsId, name, surname, phone);
        boolean result = clientDao.saveClient(client);
        if (result) {
            System.out.println("Client saved: " + client);
        }
    }

    @Override
    public void deleteClient() {

    }
}
