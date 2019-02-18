package com.riaboshapka.dao.impl;

import com.riaboshapka.dao.ClientDao;
import com.riaboshapka.domain.Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientDaoImpl implements ClientDao {

    // обєкт є тільки в одному екземплярі
    private static ClientDao clientDao = new ClientDaoImpl();

    private Map<Long, Client> map = new HashMap<>();
    private static long generator = 0;


    // Робимо сінгелтон
    private ClientDaoImpl() {
    }


    @Override
    public boolean saveClient(Client client) {
        // збереження даних в емуляції бази кожного разу нового клієнта
        System.out.println("Saving.... Please wait");
        client.setId(generator++);
        map.put(client.getId(), client);
        return true;
    }

    // отримує із мапи значення всіх клієнтів, конвертуємо в список і повертаємо на верх, тобто копіюємо із
    // верхньої колекції сюди і передаємо далі
    @Override
    public List<Client> getAllClients() {
        return new ArrayList<>(map.values());
    }

    // фабричні методи щоб створюовати приватний конструктор повертає силку тільки на один обєкт
    public static ClientDao getInstance() {
        return clientDao;
    }
}
