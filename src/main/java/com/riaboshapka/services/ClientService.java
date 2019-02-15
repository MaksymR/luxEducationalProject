package com.riaboshapka.services;

public interface ClientService {

    /**
     * create client
     *
     * @param name    of client
     * @param surname of client
     * @param phone   of client
     */
    void createClient(String name, String surname, String phone);

    /**
     * delete client
     */
    void deleteClient();

}
