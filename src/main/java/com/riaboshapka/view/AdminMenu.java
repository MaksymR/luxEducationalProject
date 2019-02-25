package com.riaboshapka.view;

import com.riaboshapka.domain.Client;
import com.riaboshapka.services.ClientService;

import java.io.BufferedReader;
import java.io.IOException;

public class AdminMenu {

    private final BufferedReader br;
    private final ClientService clientService;

    public AdminMenu(BufferedReader br, ClientService clientService) {
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
                case "4":
                    System.out.println("All clients:");
                    showAllClients();
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
        System.out.println("Input age:");
        int age = readInteger();
        System.out.println("Input phone number: ");
        String phoneNumber = br.readLine();
        System.out.println("Input email");
        String email = br.readLine();
        clientService.createClient(name, surname, age, phoneNumber, email);
    }


    private void showMenu() {
        System.out.println("1. Add client");
        System.out.println("2. Modify client");
        System.out.println("3. Remove client");
        System.out.println("4. List all clients");
        System.out.println();

        System.out.println("5. Add product");
        System.out.println("6. Modify product");
        System.out.println("7. Remove product");
        System.out.println("8. List all product");
        System.out.println();
        // everything does by id for the orders for the admin
        System.out.println("9. Modify order");
        System.out.println("10. Remove order");
        System.out.println("11. List all order");

        System.out.println("R. Return");
        System.out.println("E. Exit");
    }

    private int readInteger() {
        try {
            return Integer.parseInt(br.readLine());
        } catch (IOException | NumberFormatException ex) {
            System.out.println("Input number please!!!");
            // recursive call
            return readInteger();
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
                System.out.println("Input age:");
                int age = readInteger();
                System.out.println("Input phone number: ");
                String phoneNumber = br.readLine();
                System.out.println("Input email");
                String email = br.readLine();
                clientService.modifyClient(id, name, surname, age, phoneNumber, email);
                return;
            } else {
                System.out.println("Choose \"1. Add client\"");
            }
        }
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

    private void showAllClients() {
        for (Client client : clientService.getAllClients()) {
            System.out.println(client);
        }
    }
}
