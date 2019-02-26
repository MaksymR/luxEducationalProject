package com.riaboshapka.view;

import com.riaboshapka.domain.Client;
import com.riaboshapka.domain.Order;
import com.riaboshapka.domain.Product;
import com.riaboshapka.services.ClientService;
import com.riaboshapka.services.OrderService;
import com.riaboshapka.services.ProductService;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;

public class AdminMenu {

    private final BufferedReader br;
    private final ClientService clientService;
    private final ProductService productService;
    private final OrderService orderService;

    public AdminMenu(BufferedReader br, ClientService clientService,
                     ProductService productService, OrderService orderService) {
        this.br = br;
        this.clientService = clientService;
        this.productService = productService;
        this.orderService = orderService;
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
                case "3":
                    deleteClient();
                    break;
                case "4":
                    System.out.println("All clients:");
                    showAllClients();
                    break;
                case "5":
                    createProduct();
                    break;
                case "6":
                    modifyProduct();
                    break;
                case "7":
                    deleteProduct();
                    break;
                case "8":
                    System.out.println("All products:");
                    showAllProducts();
                    break;
                case "11":
                    showAllOrders();
                    break;
                case "E":
                    return;
                default:
                    System.out.println("wrong input!!!");
            }
        }

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


    private void modifyClient() throws IOException {
        System.out.println("Input client's ID for modify: ");
        long id = readLongId();
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

    private void deleteClient() {
        System.out.println("Input client's ID for remove: ");
        long id = readLongId();
        for (Client client : clientService.getAllClients()) {
            long tempId = client.getId();
            if(tempId == id) {
                clientService.deleteClient(id);
                return;
            } else {
                System.out.println("Choose correct Id of client for deleting");
            }
        }
    }

    private void showAllClients() {
        for (Client client : clientService.getAllClients()) {
            System.out.println(client);
        }
    }

    private void createProduct() throws IOException {
        System.out.println("Input product's name: ");
        String productName = br.readLine();
        System.out.println("Input product's price:");
        BigDecimal productPrice = readBigDecimal();
        productService.createProduct(productName, productPrice);
    }

    private void modifyProduct() throws IOException {
        System.out.println("Input product's ID for modify: ");
        long id = readLongId();
        for (Product product : productService.getAllProducts()) {
            long tempId = product.getId();
            if (tempId == id) {
                System.out.println("Input name: ");
                String productName = br.readLine();
                System.out.println("Input product's price:");
                BigDecimal productPrice = readBigDecimal();
                productService.modifyProduct(id, productName, productPrice);
                return;
            } else {
                System.out.println("Choose \"5. Add product\"");
            }
        }
    }

    private void deleteProduct() {
        System.out.println("Input product's ID for remove: ");
        long id = readLongId();
        for (Product product : productService.getAllProducts()) {
            long tempId = product.getId();
            if (tempId == id) {
                productService.delete(id);
                return;
            } else {
                System.out.println("Choose correct Id of product for deleting");
            }
        }
    }


    private void showAllProducts() {
        for (Product product : productService.getAllProducts()) {
            System.out.println(product);
        }
    }

    private void showAllOrders() {
        for (Order order : orderService.getAllOrders()) {
            System.out.println(order);
        }
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

    private long readLongId() {
        try {
            return Long.parseLong(br.readLine());
        } catch (IOException | NumberFormatException ex) {
            System.out.println("Input number please!!!");
            // recursive call
            return readLongId();
        }
    }

    private BigDecimal readBigDecimal() {
        try {
            return BigDecimal.valueOf(Long.parseLong(br.readLine()));
        } catch (IOException | NumberFormatException ex) {
            System.out.println("Input number please!!!");
            // recursive call
            return readBigDecimal();
        }
    }
}
