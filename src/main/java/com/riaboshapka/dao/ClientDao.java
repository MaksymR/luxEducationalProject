package com.riaboshapka.dao;

import com.riaboshapka.domain.Client;

import java.util.List;

public interface ClientDao {

    /**
     * save client
     *
     * @param client for save
     * @return true if client is saved
     */
    boolean saveClient(Client client);

    /**
     * modify client
     *
     * @param id of client for save
     * @param client for save
     * @return true if client is modified
     */
    boolean modifyClient(long id, Client client);

    /**
     * to read a list of all clients
     *
     * @return list of all clients
     */
    List<Client> getAllClients();

}