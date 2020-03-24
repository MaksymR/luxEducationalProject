package com.riaboshapka.view;

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
                    System.out.println("Modify client");
                    break;
                case "E":
                    return;
                default:
                    System.out.println("wrong input!!!");
            }
        }

    }

    private void createClient() throws IOException {
        System.out.println("Input name: ");
        String name = br.readLine();
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

}

