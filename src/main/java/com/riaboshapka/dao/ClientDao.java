package com.riaboshapka.dao;

import com.riaboshapka.domain.Client;

import java.util.List;

public interface ClientDao {

    boolean saveClient(Client client);

    /**
     * to read a list of all clients
     *
     * @return list of all clients
     */
    List<Client> getAllClients();

}