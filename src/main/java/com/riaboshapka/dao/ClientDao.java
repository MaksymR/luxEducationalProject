package com.riaboshapka.dao;

import com.riaboshapka.domain.Client;

import java.util.List;

public interface ClientDao {

    boolean saveClient(Client client);

    // для зчитування
    List<Client> getAllClients();

}