package com.riaboshapka.services;

import com.riaboshapka.domain.Client;

import java.util.List;

public interface ClientService {

    /* Д/З
     * add documentation
     * описуємо що робить даний метод
     * */
    void createClient(String name, String surname, String phone);

    // мотод для адміна котрий вводить повну інфу
    void createClient(String name, String surname, int age, String phone, String email);


    List<Client> getAllClients();


    /*
     * Д/З
     * описуємо що робить метод
     * */
    void deleteClient();


}
