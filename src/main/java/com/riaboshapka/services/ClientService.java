package com.riaboshapka.services;

import com.riaboshapka.domain.Client;

import java.util.List;

public interface ClientService {

    /**
     * create client
     *
     * @param name of client
     * @param surname of client
     * @param phone of client
     */
    void createClient(String name, String surname, String phone);

    /**
     * a method for the admin which introduces the full information
     *
     * @param name of client
     * @param surname of client
     * @param age of client
     * @param phone of client
     * @param email of client
     */
    void createClient(String name, String surname, int age, String phone, String email);


    List<Client> getAllClients();


    /**
     * delete client
     */
    void deleteClient();


}
