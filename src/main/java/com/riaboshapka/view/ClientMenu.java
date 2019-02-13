package com.riaboshapka.view;

import com.riaboshapka.services.ClientService;
import com.riaboshapka.services.impl.ClientServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientMenu {

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private ClientService clientService = new ClientServiceImpl();

    public void show() throws IOException {

        while (true) {
            showMenu();
            switch (br.readLine()) {
                case "1":
                    System.out.println("Product added");
                    break;
                case "2":
                    System.out.println("Product deleted");
                    break;
                case "3":
                    System.out.println("Show list of all products");
                    break;
                case "4":
                    System.out.println("Got order");
                    break;
                case "5":
                    System.out.println("Deleted order");
                case "0":
                    return;
                default:
                    System.out.println("Wrong imput!!!");
            }
        }

    }

    private void showMenu() {
        System.out.println("1. Add product");
        System.out.println("2. Delete product");
        System.out.println("3. List all list of all products");
        System.out.println("4. Get order");
        System.out.println("5. Delete order");
        System.out.println("9. Return");
        System.out.println("0. Exit");
    }

}
