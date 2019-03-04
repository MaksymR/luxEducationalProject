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
import java.util.ArrayList;
import java.util.List;

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
                case "9":
                    modifyOrder();
                    break;
                case "10":
                    deleteOrder();
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
        showAllClients();
        System.out.println("Input client's ID for modify: ");
        long id = readLongId();
        for (Client client : clientService.getAllClients()) {
            long tempId = client.getId();
            if (tempId == id) {
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
            }
        }
    }

    private void deleteClient() {
        showAllClients();
        System.out.println("Input client's ID for remove: ");
        long id = readLongId();
        for (Client client : clientService.getAllClients()) {
            long tempId = client.getId();
            if (tempId == id) {
                clientService.deleteClient(id);
                return;
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
        showAllProducts();
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
            }
        }
    }

    private void deleteProduct() {
        showAllProducts();
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


    private void modifyOrder() {
        showAllOrders();
        System.out.println("Input order's ID for modify:");
        long id = readLongId();
        for (Order order : orderService.getAllOrders()) {
            long tempId = order.getId();
            if (tempId == id) {
                Client clientForModifyOrder = getClientForModifyOrder();
                List<Product> productsListForModifyOrder = getProductsListForModifyOrder();
                orderService.modifyOrder(id, clientForModifyOrder, productsListForModifyOrder);
                return;
            }
        }
    }

    private Client getClientForModifyOrder() {
        Client clientForModifiedOrder = null;
        showAllClients();
        System.out.println("Enter client's ID for modify:");
        long clientId = readLongId();
        for (Client client : clientService.getAllClients()) {
            long clientTempId = client.getId();
            if (clientTempId == clientId) {
                clientForModifiedOrder = client;
            }
        }
        return clientForModifiedOrder;
    }

    private List<Product> getProductsListForModifyOrder() {
        showAllProducts();
        List<Product> listProducts = new ArrayList<>();
        long productId;
        boolean exitFromWhile = true;
        while (exitFromWhile) {
            System.out.println("Enter product's ID for add into the new order or \"-1\"-for exit)");
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

    private void deleteOrder() {
        showAllOrders();
        System.out.println("Input order's ID for remove: ");
        long id = readLongId();
        for (Order order : orderService.getAllOrders()) {
            long tempId = order.getId();
            if (tempId == id) {
                orderService.delete(id);
                return;
            } else {
                System.out.println("Choose correct Id of order for deleting");
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
