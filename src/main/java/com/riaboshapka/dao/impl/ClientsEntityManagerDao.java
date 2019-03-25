package com.riaboshapka.dao.impl;


import com.riaboshapka.dao.ClientDao;
import com.riaboshapka.domain.Client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

// can give name ClientEMDao
public class ClientsEntityManagerDao  implements ClientDao {

    private EntityManager entityManager;

    public ClientsEntityManagerDao(EntityManager entityManager) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence-unit");
        this.entityManager = factory.createEntityManager();
    }

    @Override
    public boolean saveClient(Client client) {

        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }

    @Override
    public boolean modifyClient(long id, Client client) {
        return false;
    }

    @Override
    public List<Client> getAllClients() {
        List<Client> resultList = entityManager.createQuery("from Client", Client.class).getResultList();
        return resultList;
    }

    @Override
    public boolean deleteClient(long id) {
        return false;
    }

}
