package com.riaboshapka.view;

import com.riaboshapka.domain.Client;
import com.riaboshapka.domain.Product;
import com.riaboshapka.services.OrderService;
import com.riaboshapka.services.impl.OrderServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ClientMenu {

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private OrderService orderService = new OrderServiceImpl();


    public void show() throws IOException {

        while (true) {
            showMenu();
            switch (br.readLine()) {
                case "1":
                    createOrder();
                    break;
                case "2":
                    System.out.println("Show the order list");
                    break;
                case "3":
                    deleteOrder();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Wrong input!!!");
            }
        }

    }

    private void showMenu() {
        System.out.println("1. Create an order list");
        System.out.println("2. Get the order list");
        System.out.println("3. Delete the order list");
        System.out.println("9. Return");
        System.out.println("0. Exit");
    }

    private Client createClient() throws IOException {
        System.out.println("Input client's name: ");
        String name = br.readLine();
        System.out.println("Input client's surname: ");
        String surName = br.readLine();
        System.out.println("Input client's phone number: ");
        String phoneNumber = br.readLine();
        Client client = new Client(name, surName, phoneNumber);
        return client;
    }

    private Product createProduct() throws IOException {
        System.out.println("Input product's name: ");
        String productName = br.readLine();
        System.out.println("Input product's price: ");
        BigDecimal productPrice = new BigDecimal(Integer.parseInt(br.readLine()));
        Product product = new Product(productName, productPrice);
        return product;
    }

    private List<Product> createProductsList() throws IOException {
        boolean isAdding = true;
        List<Product> productList = new ArrayList<>();
        while (isAdding) {
            productList.add(createProduct());
            System.out.println("Do you want to add next product?");
            System.out.println("Press \"y\" or \"n\" ");
            if (br.readLine().equals("n")) {
                isAdding = false;
            }

        }
        return productList;
    }

    private void createOrder() throws IOException {
        Client client = createClient();
        List<Product> productsList = createProductsList();
        orderService.createOrder(client, productsList);
    }

    private void deleteOrder() {
        orderService.deleteOrder();
    }
}
