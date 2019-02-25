package com.riaboshapka.view;

import com.riaboshapka.domain.Client;
import com.riaboshapka.services.ClientService;

import java.io.BufferedReader;
import java.io.IOException;

public class ClientMenu {

    private final BufferedReader br;
    private final ClientService clientService;

    public ClientMenu(BufferedReader br, ClientService clientService) {
        this.br = br;
        this.clientService = clientService;
    }

    public void show() throws IOException {


        while (true) {
            showMenu();
            switch (br.readLine()) {
                case "1":
                    createClient();
                    break;
                case "2":
                    modifyClient();
                    break;
                case "E":
                    return;
                default:
                    System.out.println("wrong input!!!");
            }
        }

    }

    private void modifyClient() throws IOException {

        System.out.println("Input client's ID for modify: ");
        long id = readLongId();
        //Client clientForModify = null;
        for (Client client : clientService.getAllClients()) {
            long tempId = client.getId();
            if(tempId == id) {
                System.out.println("Input name: ");
                String name = br.readLine();
                System.out.println("Input surname: ");
                String surname = br.readLine();
                System.out.println("Input phone number: ");
                String phoneNumber = br.readLine();
                clientService.modifyClient(id, name, surname, phoneNumber);
                return;
            } else {
                System.out.println("Choose \"1. Register\"");
            }
        }

    }

    private void createClient() throws IOException {
        System.out.println("Input name: ");
        String name;
        name = br.readLine();
        System.out.println("Input surname: ");
        String surname = br.readLine();
        System.out.println("Input phone number: ");
        String phoneNumber = br.readLine();
        clientService.createClient(name, surname, phoneNumber);
    }

    private void showMenu() {
        System.out.println("1. Register");
        System.out.println("2. Modify"); // by ID
        System.out.println();
        System.out.println("3. List products");
        System.out.println("4. Add order");
        System.out.println("5. List orders");
        System.out.println();
        System.out.println("R. Return");
        System.out.println("E. Exit");
    }

    private long readLongId() {
        try {
            return Long.parseLong(br.readLine());
        } catch (IOException | NumberFormatException ex) {
            System.out.println("Input number please!!!");
            // recursive call
            return readLongId();
        }
    }

}

