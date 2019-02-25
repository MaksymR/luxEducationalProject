package com.riaboshapka.dao.impl;

import com.riaboshapka.dao.ClientDao;
import com.riaboshapka.domain.Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientDaoImpl implements ClientDao {

    // the object is only in one copy
    private static ClientDao clientDao;

    private Map<Long, Client> map = new HashMap<>();
    private static long generator = 0;


    // "singleton"
    private ClientDaoImpl() {
    }


    @Override
    public boolean saveClient(Client client) {
        // saving data (a new client) in database emulation every time
        System.out.println("Saving.... Please wait");
        client.setId(generator++);
        map.put(client.getId(), client);
        return true;
    }

    @Override
    public boolean modifyClient(long id, Client client) {
        System.out.println("Saving.... Please wait");
        Client oldClient = map.get(id);
        map.replace(id, oldClient, client);
        return true;
    }

    // receives from the map the value of all clients, converts to a list and returns to the top,
    // that is we copy from the top collection here and transmit it further
    @Override
    public List<Client> getAllClients() {
        return new ArrayList<>(map.values());
    }

    // factory methods to create a private constructor, which returns a link to only one object
    public static ClientDao getInstance() {
        if (clientDao == null) {
            clientDao = new ClientDaoImpl();
        }
        return clientDao;
    }
}
