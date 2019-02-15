package com.riaboshapka.dao;

import com.riaboshapka.domain.Client;

public interface ClientDao {

    /**
     * @param client for DB
     * @return true if client is created and false in other case
     */
    boolean saveClient(Client client);

}
