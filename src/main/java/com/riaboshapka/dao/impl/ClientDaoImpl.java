package com.riaboshapka.dao.impl;

import com.riaboshapka.dao.ClientDao;
import com.riaboshapka.domain.Client;

public class ClientDaoImpl implements ClientDao {
    @Override
    public boolean saveClient(Client client) {
        System.out.println("Saving... Please wait");
        return true;
    }
}
