package com.riaboshapka.view;

import com.riaboshapka.domain.Client;
import com.riaboshapka.domain.Order;
import com.riaboshapka.domain.Product;
import com.riaboshapka.services.ClientService;
import com.riaboshapka.services.OrderService;
import com.riaboshapka.services.ProductService;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientMenu {

    private final BufferedReader br;
    private final ClientService clientService;
    private final ProductService productService;
    private final OrderService orderService;

    public ClientMenu(BufferedReader br, ClientService clientService,
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
                    System.out.println("All products:");
                    showAllProducts();
                    break;
                case "4":
                    createOrder();
                    break;
                case "5":
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

    private void modifyClient() throws IOException {
        showAllClients();
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
            }
        }

    }

    private void showAllClients() {
        for (Client client : clientService.getAllClients()) {
            System.out.println(client);
        }
    }

    private void showAllProducts() {
        for (Product product : productService.getAllProducts()) {
            System.out.println(product);
        }
    }

    private void createOrder() {
        //showAllProducts();
        showAllClients();
        System.out.println("Input client's ID for create client's order: ");
        long clientId = readLongId();
        showAllProducts();
        List<Product> listProducts = createProductsList();
        Client client = getClientById(clientId);
        orderService.createOrder(client, listProducts);
    }

    private Client getClientById(long clientId) {
        Client client = null;
        for (Client tempClient : clientService.getAllClients()) {
            long tempId = tempClient.getId();
            if(tempId == clientId) {
                client = tempClient;
            }
        }
        if (client == null) {
            System.out.println("Choose \"1. Register\"");
        }
        return client;
    }

    private List<Product> createProductsList() {
        List<Product> listProducts = new ArrayList<>();
        long productId;
        boolean exitFromWhile = true;
        while (exitFromWhile) {
            System.out.println("Enter product's ID for adding it to order or \"-1\"-for exit)");
            productId = readLongId();
            if (productId != -1) {
                for (Product product : productService.getAllProducts()) {
                    long tempProductId = product.getId();
                    if (tempProductId == productId) {
                        listProducts.add(product);
                    }
                }
            } else {
                exitFromWhile = false;
            }
        }
        return listProducts;
    }

    private void showAllOrders() {
        for (Order order : orderService.getAllOrders()) {
            System.out.println(order);
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

}

