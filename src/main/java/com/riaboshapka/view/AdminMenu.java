package com.riaboshapka.view;

import com.riaboshapka.services.ClientService;
import com.riaboshapka.services.ProductService;
import com.riaboshapka.services.impl.ClientServiceImpl;
import com.riaboshapka.services.impl.ProductServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class AdminMenu {

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private ClientService clientService = new ClientServiceImpl();
    private ProductService productService = new ProductServiceImpl();

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
                case "3":
                    System.out.println("Remove client");
                    break;
                case "4":
                    System.out.println("List all clients");
                    break;
                case "5":
                    createProduct();
                    break;
                case "6":
                    deleteProduct();
                    break;
                case "7":
                    System.out.println("List all products");
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Wrong input!!!");
            }
        }
    }

    private void showMenu() {
        System.out.println("1. Add client");
        System.out.println("2. Modify client");
        System.out.println("3. Remove client");
        System.out.println("4. List all clients");
        System.out.println("5. Create product");
        System.out.println("6. Delete product");
        System.out.println("7. List all products");
        System.out.println("9. Return");
        System.out.println("0. Exit");
    }

    private void createClient() throws IOException {
        System.out.println("Input client's id: ");
        long clientsId = Long.parseLong(br.readLine());
        System.out.println("Input client's name: ");
        String name = br.readLine();
        System.out.println("Input client's surname: ");
        String surName = br.readLine();
        System.out.println("Input client's phone number: ");
        String phoneNumber = br.readLine();
        clientService.createClient(clientsId, name, surName, phoneNumber);
    }

    private void createProduct() throws IOException {
        System.out.println("Input product's id: ");
        Long prouductId = Long.parseLong(br.readLine());
        System.out.println("Input product's name: ");
        String productName = br.readLine();
        System.out.println("Input product's price: ");
        BigDecimal productPrice = new BigDecimal(Integer.parseInt(br.readLine()));
        productService.createProduct(prouductId, productName, productPrice);
    }

    private void deleteProduct() {
        productService.deleteProduct();
    }

}
